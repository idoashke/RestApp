package com.example.restapp.ui.views

import android.annotation.SuppressLint
import com.example.restapp.ui.files.DishesListItem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.restapp.models.Dish
//import com.example.restapp.repository.DishesData
//import com.example.restapp.ui.files.RestMenuContent
import com.example.restapp.ui.theme.CategoryFont
import com.example.restapp.ui.theme.LightOrange
import com.example.restapp.ui.theme.blue
import com.example.restapp.ui.theme.graySurface


@ExperimentalFoundationApi
@Composable
//fun MenuPageContent(navigateToProfile: (Dish) -> Unit) {
fun MenuPage(dishes: List<Dish>, navController: NavHostController) {
//    val dp = DishesRepository()
//    val dishes = remember { dp.getAllData() }


//    val dishes = remember { DishesData.allDishes }
    val sections = listOf("Starters", "Mains", "Desserts", "Drinks")

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        sections.forEach { section ->
            stickyHeader {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    elevation = 2.dp,
                    backgroundColor = MaterialTheme.colors.primary,
//        backgroundColor = Color.White,
                    shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                ){
                    Row() {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "$section", style = CategoryFont.h6, color = Color.White)
//                            Text(text = "$section", style = MaterialTheme.typography.h6, color = blue)
                        }
                    }
                }
            }
            items(
                items = dishes,
                itemContent = {
                    if (it.category == section) {
//                        DishesListItem(dish = it, navigateToProfile)
                        DishesListItem(dish = it, navController)
                    }
                }
            )
        }
    }
}
