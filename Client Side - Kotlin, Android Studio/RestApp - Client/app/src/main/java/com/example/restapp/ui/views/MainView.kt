package com.example.restapp.ui.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restapp.models.Admin
import com.example.restapp.models.Business
import com.example.restapp.models.Dish
import com.example.restapp.models.User
//import com.example.restapp.repository.DishesData
import com.example.restapp.routes.Routes
import com.example.restapp.sql.retrieveDataFromSql

@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
//fun MainView(user: User , dishes: List<Dish>){
//fun MainView(user: User){
fun MainView(){
    val navController = rememberNavController()

    var dishes = remember { dishesBuilder(retrieveDataFromSql("dish", "get", "", "")) }
    var user : User
    var admin : Admin
    var cart : MutableList<Dish> = remember {mutableListOf() }
    var business = remember { businessBuilder(retrieveDataFromSql("business", "get", "", "")) }


    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }

        composable(Routes.Signup.route) {
            SignUp(navController = navController)
        }

        composable(Routes.Menu.route) {
            var userEmail = navController.previousBackStackEntry?.savedStateHandle?.get<String>("userEmail")
            userEmail?.let {
                Log.d("userEmail " , userEmail)
                user = remember { userBuilder(retrieveDataFromSql("user", "get", userEmail, "")) }
                MenuPage(dishes = dishes, navController = navController)
            }
//            MenuPage(dishes = dishes, navController = navController)
        }

        composable(Routes.Dish.route) {

            var dish = navController.previousBackStackEntry?.savedStateHandle?.get<Dish>("dish")
            dish?.let {
                DishPage(dish = dish, cart = cart, navController = navController)
            }
        }

        composable(Routes.Admin.route) {
            var adminEmail = navController.previousBackStackEntry?.savedStateHandle?.get<String>("adminEmail")
            adminEmail?.let {
                Log.d("adminEmail " , adminEmail)
                admin = remember { adminBuilder(retrieveDataFromSql("admin", "get", adminEmail, "")) }
                AdminPage(navController = navController)
            }
//            AdminPage(navController = navController)
        }

        composable(Routes.EditDetails.route) {
            EditBusDetails(dishes = dishes, business = business, navController = navController)
//            EditBusDetails(dishes = dishes, business = business, navController = navController)
        }

        composable(Routes.EditUser.route) {
//            EditUserProfile(navController = navController)
        }


        composable(Routes.AdminLogin.route) {
            AdminLoginPage(navController = navController)
        }

        composable(Routes.Cart.route) {
            CartPage(cart = cart, navController = navController)
        }

    }
}

fun dishesBuilder(answer: String) : MutableList<Dish>{
    var dishes : MutableList<Dish> = mutableListOf()
    var dishesString = answer.split(";").toMutableList()
    dishesString.removeLast()
//    Log.d("dishcheck", "dish check is " + answer)
    for (dish in dishesString){
//        Log.d("Before splitting ", dish)
        var dishString = dish.split("~")
        var dishToAdd = Dish(dishString[0].toLong(), dishString[1], dishString[2], dishString[3], dishString[4].toInt(), dishString[5])
        dishes.add(dishToAdd)
    }
    return dishes
}

fun userBuilder(answer: String) : User {
    var userString = answer.split("~")
    var user = User(userString[0], userString[1], userString[2], userString[3], userString[4], userString[5], userString[6], userString[7])
    return user
}

fun adminBuilder(answer: String) : Admin {
    var adminString = answer.split("~")
    var admin = Admin(adminString[0], adminString[1], adminString[2], adminString[3])
    return admin
}

fun businessBuilder(answer: String) : Business {
    var businessString = answer.split("~")
    var business = Business(businessString[0], businessString[1], businessString[2])
    return business
}