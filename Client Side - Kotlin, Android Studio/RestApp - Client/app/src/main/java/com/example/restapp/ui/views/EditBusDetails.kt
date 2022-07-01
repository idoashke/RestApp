package com.example.restapp.ui.views


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.restapp.R
//import com.example.restapp.activities.dishes
import com.example.restapp.models.Business
import com.example.restapp.models.Dish
import com.example.restapp.sql.retrieveDataFromSql
import com.example.restapp.ui.files.AnimatingFabContent
import com.example.restapp.ui.theme.Purple500

@Composable
fun EditBusDetails(dishes: MutableList<Dish>, business: Business, navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top appbar
//        TopAppbarProfile(context = LocalContext.current.applicationContext)
        ProfileEcommerce(business, dishes)
    }


}

private val optionsList: ArrayList<OptionsData> = ArrayList()

@Composable
fun TopAppbarProfile(context: Context) {
    TopAppBar(
        title = {
            Text(
                text = "Profile",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context, "Nav Button", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Go back",
                )
            }
        }
    )
}

@Composable
fun ProfileEcommerce(business: Business, dishes : MutableList<Dish>, context: Context = LocalContext.current.applicationContext) {
    val scrollState = rememberScrollState()

    // This indicates if the optionsList has data or not
    // Initially, the list is empty. So, its value is false.
    var listPrepared by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            optionsList.clear()

            // Add the data to optionsList
//            prepareOptionsData(business)

            listPrepared = true
        }
    }

    if (listPrepared) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

            item {
//                 User's image, name, email and edit button
//                    busDetails(context = context)
                    busDetails(business, context = context)
            }

            // Show the options
//            items(optionsList) { item ->
////                    OptionsItemStyle(item = item, context = context)
//                    OptionsItemStyle(item = item, context = context, business)
//            }


            items(dishes) { item ->
//                    OptionsItemStyle(item = item, context = context)
                            busItemStyle(dish = item, context = context)
            }

            item {
//                BoxWithConstraints() {
//                    AddNewDish(
//                        dishes = dishes,
//                        extended = scrollState.value == 0,
//                        modifier = Modifier.align(Alignment.BottomEnd),
//                        context = context
//
//                    )
//                }
                AddNewDish(
                    dishes = dishes,
                    extended = scrollState.value == 0,
                    context = context

                )
            }

        }
    }
}

// This composable displays user's image, name, email and edit button
@Composable
private fun busDetails(business: Business, context: Context) {

    val openDialog = remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(business.name) }
    var city by remember { mutableStateOf(business.city) }
    var address by remember { mutableStateOf(business.address) }


    if (openDialog.value) {
        var oldName = business.name
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Edit Business Details")
            },
            text = {
                Column() {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(text = ("Name")) }

                    )
//                    Text("Custom Text")
//                    Checkbox(checked = false, onCheckedChange = {})
                    TextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text(text = ("City")) }
                    )
                    TextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text(text = ("Address")) }
                    )
                }
            },
            buttons = {
                Row(
//                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
//                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
//                        modifier = Modifier.size(15.dp),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Cancel")
                    }
                    Button(
//                        modifier = Modifier.size(15.dp),
                        onClick = {
                            if (name != "" && city != "" && address != "") {
                                business.name = name
                                business.city = city
                                business.address = address
                                var objData = name+","+city+","+address
                                var answer = retrieveDataFromSql("business", "update",oldName, objData)
                                if (answer.equals("success")){
                                    Toast.makeText(context, "business updated successfully!", Toast.LENGTH_SHORT).show()

                                } else {
                                    Toast.makeText(context, "Failed to update business..", Toast.LENGTH_SHORT).show()
                                }
                                openDialog.value = false
                            }
                            else {
                                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()

                            }
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        )
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_baseline_business_24),
                contentDescription = "Business Picture",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
            )

            Column(
                modifier = Modifier
                    .weight(weight = 3f, fill = false)
                    .padding(start = 16.dp)
            ) {

                // User's name
                Text(
                    text = business.name,
                    style = TextStyle(
                        fontSize = 22.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold)),
                        fontFamily = FontFamily(Font(R.font.roboto_medium, FontWeight.Normal)),
//                        fontFamily = FontFamily.Cursive,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                // User's email
                Text(
//                    text = "email123@email.com",
                    text = business.city,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.roboto, FontWeight.Normal)),
//                        fontFamily = FontFamily.Cursive,
                        color = Color.Gray,
                        letterSpacing = (0.8).sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Business Address
                Text(
//                    text = "email123@email.com",
                    text = business.address,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.roboto, FontWeight.Normal)),
//                        fontFamily = FontFamily.Cursive,
                        color = Color.Gray,
                        letterSpacing = (0.8).sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Edit button
            IconButton(
                modifier = Modifier
                    .weight(weight = 1f, fill = false),
                onClick = {
                    openDialog.value = true
//                    Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show() ////////////////////////////////////////////////////////////////////
                }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit Details",
                    tint = MaterialTheme.colors.primary
                )
            }

        }
    }
}

@Composable
//private fun OptionsItemStyle(item: OptionsData, context: Context) {
private fun busItemStyle(dish: Dish, context: Context) {
    val openDialog = remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(dish.name) }
    var category by remember { mutableStateOf(dish.category) }
    var description by remember { mutableStateOf(dish.description) }
    var price by remember { mutableStateOf(dish.price.toString()) }
    var image by remember { mutableStateOf(dish.dishImageId.toString()) }
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(enabled = true) {
                if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Edit Dishes Details")
                    },
                    text = {
                        Column() {
                            TextField(
                                value = name,
                                onValueChange = { name = it },
                                label = { Text(text = ("Name")) }

                            )
                            TextField(
                                value = category,
                                onValueChange = { category = it },
                                label = { Text(text = ("Category")) }
                            )
                            TextField(
                                value = description,
                                onValueChange = { description = it },
                                label = { Text(text = ("Description")) }
                            )
                            TextField(
                                value = price,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                onValueChange = { price = it },
                                label = { Text(text = ("Price")) }
                            )
                            TextField(
                                value = image,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                onValueChange = { image = it },
                                label = { Text(text = ("image")) }
                            )
                        }
                    },
                    buttons = {
                        Row(
//                    modifier = Modifier.padding(all = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
//                    horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
//                        modifier = Modifier.size(15.dp),
                                onClick = { openDialog.value = false }
                            ) {
                                Text("Cancel")
                            }
                            Button(
//                        modifier = Modifier.size(15.dp),
                                onClick = {
//                                    if (name != "" && category != "" && description != "" && price != "" && image != "") {
                                    if (name != "" && category != "" && description != "" && price != "" ) {
                                        dish.name = name
                                        dish.category = category
                                        dish.description = description
                                        dish.price = price.toInt()
//                                        dish.dishImageId = image.toInt()
                                        dish.dishImageId = ""
                                        var objData = dish.id.toString()+","+name+","+category+","+description+
                                                ","+price+","+image
                                        Log.d("dish update test ", objData)
                                        var answer = retrieveDataFromSql("dish", "update",dish.id.toString(), objData)
                                        if (answer.equals("success")){
                                            Toast.makeText(context, "dish updated successfully!", Toast.LENGTH_SHORT).show()

                                        } else {
                                            Toast.makeText(context, "Failed to update dish..", Toast.LENGTH_SHORT).show()
                                        }
                                        openDialog.value = false
                                    } else {
                                        Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()

                                    }
                                }
                            ) {
                                Text("Save")
                            }
                        }
                    }
                )
//                Toast
//                    .makeText(context, item.title, Toast.LENGTH_SHORT)
//                    .show()
            }
//    ) {

        // Icon


        Row(
            modifier = Modifier
                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Icon(
//                modifier = Modifier
//                    .size(32.dp),
//                imageVector = Icons.Outlined.Menu, //item.icon,
//                contentDescription = dish.name,
//                tint = MaterialTheme.colors.primary
//            )
            Image(
                painter = rememberAsyncImagePainter(dish.dishImageId),
                contentDescription = "dish Picture",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
                    .padding(start = 10.dp)
            )
            Column(
                modifier = Modifier
                    .weight(weight = 3f, fill = false)
                    .padding(start = 16.dp)
            ) {

                // Title
                Text(
                    text = dish.name,
                    style = TextStyle(
                        fontSize = 18.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_medium, FontWeight.Medium)
                        fontFamily = FontFamily(Font(R.font.bangers, FontWeight.Normal))
                    )

                )

                Spacer(modifier = Modifier.height(2.dp))

                // Sub title
                Text(
                    text = dish.category,
                    style = TextStyle(
                        fontSize = 16.sp,
                        letterSpacing = (0.8).sp,
                        fontFamily = FontFamily(Font(R.font.bangers, FontWeight.Normal)),
//                        fontFamily = FontFamily.Cursive,
                        color = Color.Gray
                    )
                )

            }

            // Right arrow icon
            IconButton(
                modifier = Modifier
                    .weight(weight = 1f, fill = false),
                onClick = {
                    openDialog.value = true
//                    Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show() ////////////////////////////////////////////////////////////////////
                }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit Details",
                    tint = MaterialTheme.colors.primary,
                )
            }
        }

    }
//}

@Composable
fun AddNewDish(dishes: MutableList<Dish>, extended: Boolean, modifier: Modifier = Modifier,context : Context) {//////////////////////////////////////////////////////////
    val openDialog = remember { mutableStateOf(false) }

    var newDishId by remember { mutableStateOf("") }
    var newDishName by remember { mutableStateOf("") }
    var newDishCategory by remember { mutableStateOf("") }
    var newDishDescription by remember { mutableStateOf("") }
    var newDishPrice by remember { mutableStateOf("") }
    var newDishImage by remember { mutableStateOf("") }

    if (openDialog.value) {

        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Edit Dishes Details")
            },
            text = {
                Column() {
                    TextField(
                        value = newDishId,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { newDishId = it },
                        label = { Text(text = ("ID")) }
                    )
                    TextField(
                        value = newDishName,
                        onValueChange = { newDishName = it },
                        label = { Text(text = ("Name")) }

                    )
                    TextField(
                        value = newDishCategory,
                        onValueChange = { newDishCategory = it },
                        label = { Text(text = ("Category")) }
                    )
                    TextField(
                        value = newDishDescription,
                        onValueChange = { newDishDescription = it },
                        label = { Text(text = ("Description")) }
                    )
                    TextField(
                        value = newDishPrice,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { newDishPrice = it },
                        label = { Text(text = ("Price")) }
                    )
                    TextField(
                        value = newDishImage,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { newDishImage = it },
                        label = { Text(text = ("image")) }
                    )
                }
            },
            buttons = {
                Row(
//                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
//                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
//                        modifier = Modifier.size(15.dp),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Cancel")
                    }
                    Button(
//                        modifier = Modifier.size(15.dp),
                        onClick = {
                            var dishId = newDishId
                            var dishName = newDishName
                            var dishCategory = newDishCategory
                            var dishDescription = newDishDescription
                            var dishPrice = newDishPrice
                            var dishImage = newDishImage
                            if (dishId != "" && dishName != "" && dishCategory != "" && dishDescription != ""
                                && dishPrice != "" && dishImage != "") {

                                var objData = dishId+","+dishName+","+dishCategory+","+dishDescription+
                                        ","+dishPrice+","+dishImage
                                Log.d("dish update test ", objData)
                                var answer = retrieveDataFromSql("dish", "insert", "", objData)
                                if (answer.equals("success")){
                                    Toast.makeText(context, "Dish added successfully!", Toast.LENGTH_SHORT).show()
                                    dishes.add(Dish(dishId.toLong(), dishName, dishCategory, dishDescription, dishPrice.toInt(),dishImage))

                                } else {
                                    Toast.makeText(context, "Failed to add dish..", Toast.LENGTH_SHORT).show()
                                }
                                openDialog.value = false
                            } else {
                                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()

                            }
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        )
    }

    FloatingActionButton(
        onClick = {
            openDialog.value = true
        },
        modifier = modifier
            .padding(16.dp)
            .padding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = Purple500,
        contentColor = Color.White,
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(R.string.add_new_dish)
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.add_new_dish),
                )
            },
            extended = extended
        )
    }
}


private fun prepareOptionsData(dishes : MutableList<Dish>) {

    val appIcons = Icons.Outlined

//    for (dish : Dish in business.restDishes){
    for (dish : Dish in dishes){
            optionsList.add(
        OptionsData(
            icon = appIcons.Menu,
            title = dish.name,
            category = dish.category,
            dish = dish
        )
    )

    }

}

data class OptionsData(val icon: ImageVector, val title: String, val category: String, val dish: Dish)
//data class OptionsData(val icon: ImageVector, val title: String, val subTitle: String)

