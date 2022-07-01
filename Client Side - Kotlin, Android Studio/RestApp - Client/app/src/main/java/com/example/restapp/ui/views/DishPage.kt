package com.example.restapp.ui.views

import android.widget.Toast
import com.example.restapp.ui.files.AnimatingFabContent
import com.example.restapp.ui.files.baselineHeight

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.restapp.models.Dish
//import com.example.restapp.repository.DishesData
//import com.example.restapp.repository.DishesRepository
import com.example.restapp.ui.theme.Purple500

@Composable
//fun DishPage(dish: Dish, onNavIconPressed: () -> Unit = { }) {
fun DishPage(dish : Dish, cart : MutableList<Dish>, navController: NavHostController) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ProfileHeader(
                        scrollState,
                        dish,
                        this@BoxWithConstraints.maxHeight
                    )
                    ProfileContent(dish, this@BoxWithConstraints.maxHeight)
                }
            }
            AddToCart(
                cart = cart,
                dish = dish,
                extended = scrollState.value == 0,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

            ShowCart(
                cart = cart,
                extended = scrollState.value == 0,
                modifier = Modifier.align(Alignment.BottomStart), navController = navController)
        }
    }
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    dish: Dish,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
//        painter = painterResource(id = dish.puppyImageId),
//        painter = rememberAsyncImagePainter(dish.image),
        painter = rememberAsyncImagePainter(dish.dishImageId),

//        painter = painterResource(id = dish.dishImageId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun ProfileContent(dish: Dish, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(dish)

        ProfileProperty(stringResource(com.example.restapp.R.string.name), dish.name)

        ProfileProperty(stringResource(com.example.restapp.R.string.description), dish.description)

        ProfileProperty(stringResource(com.example.restapp.R.string.price), dish.price.toString())

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(
    dish: Dish
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            dish = dish,
            modifier = Modifier.baselineHeight(32.dp)
        )
    }
}

@Composable
private fun Name(dish: Dish, modifier: Modifier = Modifier) {
    Text(
        text = dish.name,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.caption,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}

@Composable
fun AddToCart(cart: MutableList<Dish>, dish: Dish, extended: Boolean, modifier: Modifier = Modifier) {
    var context = LocalContext.current.applicationContext
    FloatingActionButton(
        onClick = {
                  cart.add(dish)
            Toast.makeText( context, "Dish added to cart", Toast.LENGTH_SHORT).show()

        },
        modifier = modifier
            .padding(16.dp)
            .padding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = Purple500,
        contentColor = Color.White
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(com.example.restapp.R.string.add_to_cart)
                )
            },
            text = {
                Text(
                    text = stringResource(com.example.restapp.R.string.add_to_cart),
                )
            },
            extended = extended

        )
    }
}


@Composable
fun ShowCart(cart: MutableList<Dish>, extended: Boolean, modifier: Modifier = Modifier, navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("Cart")

        },
        modifier = modifier
            .padding(16.dp)
            .padding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = Purple500,
        contentColor = Color.White
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = stringResource(com.example.restapp.R.string.show_cart)
                )
            },
            text = {
                Text(
                    text = stringResource(com.example.restapp.R.string.show_cart),
                )
            },
            extended = extended

        )
    }
}