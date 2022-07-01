package com.example.restapp.ui.files

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restapp.models.Dish
import com.example.restapp.ui.theme.graySurface
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter


@Composable
fun DishesListItem(dish: Dish, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.onError,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row(Modifier.clickable {
            navController.currentBackStackEntry?.savedStateHandle?.set("dish", dish)
            navController.navigate("Dish")
        }) {
            DishImage(dish)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = dish.name, style = typography.h6)
                Text(text = dish.price.toString(), style = typography.caption)
            }
        }
    }
}

@Composable
private fun DishImage(dish: Dish) {
    Image(
        painter = rememberAsyncImagePainter(dish.dishImageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}