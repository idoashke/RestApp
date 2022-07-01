package com.example.restapp.routes

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Signup : Routes("Signup")
    object Menu : Routes("Menu")
    object Dish : Routes("Dish")
    object Admin : Routes("Admin")
    object EditDetails : Routes("EditDetails")
    object EditUser : Routes("EditUser")
    object EditBusDialog : Routes("EditBusDialog")
    object AdminLogin : Routes("AdminLogin")
    object Cart : Routes("Cart")
}
