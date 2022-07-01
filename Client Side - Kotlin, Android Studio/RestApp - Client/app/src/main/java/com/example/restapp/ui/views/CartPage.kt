package com.example.restapp.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.restapp.models.Dish
import com.example.restapp.ui.files.DishesListItem
import com.example.restapp.ui.theme.CategoryFont
import kotlin.text.Typography.section

@ExperimentalFoundationApi
@Composable
fun CartPage(cart: MutableList<Dish>, navController: NavHostController) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
            items(
                items = cart,
                itemContent = {
                        DishesListItem(dish = it, navController)
                    }
            )
    }
}
