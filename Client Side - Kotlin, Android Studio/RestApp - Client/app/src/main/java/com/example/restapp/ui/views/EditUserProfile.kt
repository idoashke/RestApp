//package com.example.restapp.ui.views
//
//
//import android.content.Context
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.outlined.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import com.example.restapp.R
//import com.example.restapp.models.Dish
//
//@Composable
//fun EditUserProfile(navController: NavHostController){
////    val name : String,
////    val city : String,
////    val address : String,
////    val restDishes : List<Dish>
//    val name = "Misada"
//    val city = "Holon"
//    val address = "some address"
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Top appbar
//        TopAppbarProfile(context = LocalContext.current.applicationContext)
//        ProfileEcommerce()
//    }
//
//
//}
//
//val optionsList: ArrayList<OptionsData> = ArrayList()
//
//@Composable
//fun TopAppbarProfile(context: Context) {
//    TopAppBar(
//        title = {
//            Text(
//                text = "Profile",
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//        },
//        backgroundColor = MaterialTheme.colors.background,
//        elevation = 4.dp,
//        navigationIcon = {
//            IconButton(onClick = {
//                Toast.makeText(context, "Nav Button", Toast.LENGTH_SHORT).show()
//            }) {
//                Icon(
//                    Icons.Filled.ArrowBack,
//                    contentDescription = "Go back",
//                )
//            }
//        }
//    )
//}
//
//@Composable
//fun ProfileEcommerce(context: Context = LocalContext.current.applicationContext) {
//
//    // This indicates if the optionsList has data or not
//    // Initially, the list is empty. So, its value is false.
//    var listPrepared by remember {
//        mutableStateOf(false)
//    }
//
//    LaunchedEffect(Unit) {
//        withContext(Dispatchers.Default) {
//            optionsList.clear()
//
//            // Add the data to optionsList
//            prepareOptionsData()
//
//            listPrepared = true
//        }
//    }
//
//    if (listPrepared) {
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//
//            item {
////                 User's image, name, email and edit button
//                UserDetails(context = context)
//            }
//
//            // Show the options
//            items(optionsList) { item ->
//                OptionsItemStyle(item = item, context = context)
//            }
//
//        }
//    }
//}
//
//// This composable displays user's image, name, email and edit button
//@Composable
//fun UserDetails(context: Context) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        // User's image
//        Image(
//            modifier = Modifier
//                .size(72.dp)
//                .clip(shape = CircleShape),
//            painter = painterResource(id = R.drawable.ic_lock),
//            contentDescription = "Your Image"
//        )
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(weight = 3f, fill = false)
//                    .padding(start = 16.dp)
//            ) {
//
//                // User's name
//                Text(
//                    text = "Victoria Steele",
//                    style = TextStyle(
//                        fontSize = 22.sp,
////                            fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold)),
//                        fontFamily = FontFamily.Cursive,
//                    ),
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//
//                Spacer(modifier = Modifier.height(2.dp))
//
//                // User's email
//                Text(
//                    text = "email123@email.com",
//                    style = TextStyle(
//                        fontSize = 14.sp,
////                            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
//                        fontFamily = FontFamily.Cursive,
//                        color = Color.Gray,
//                        letterSpacing = (0.8).sp
//                    ),
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//
//            // Edit button
//            IconButton(
//                modifier = Modifier
//                    .weight(weight = 1f, fill = false),
//                onClick = {
//                    Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show()
//                }) {
//                Icon(
//                    modifier = Modifier.size(24.dp),
//                    imageVector = Icons.Outlined.Edit,
//                    contentDescription = "Edit Details",
//                    tint = MaterialTheme.colors.primary
//                )
//            }
//
//        }
//    }
//}
//
//// Row style for options
//@Composable
//fun OptionsItemStyle(item: OptionsData, context: Context) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(enabled = true) {
//                Toast
//                    .makeText(context, item.title, Toast.LENGTH_SHORT)
//                    .show()
//            }
//            .padding(all = 16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        // Icon
//        Icon(
//            modifier = Modifier
//                .size(32.dp),
//            imageVector = item.icon,
//            contentDescription = item.title,
//            tint = MaterialTheme.colors.primary
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(weight = 3f, fill = false)
//                    .padding(start = 16.dp)
//            ) {
//
//                // Title
//                Text(
//                    text = item.title,
//                    style = TextStyle(
//                        fontSize = 18.sp,
////                            fontFamily = FontFamily(Font(R.font.roboto_medium, FontWeight.Medium))
//                        fontFamily = FontFamily.Cursive
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(2.dp))
//
//                // Sub title
//                Text(
//                    text = item.subTitle,
//                    style = TextStyle(
//                        fontSize = 14.sp,
//                        letterSpacing = (0.8).sp,
////                            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
//                        fontFamily = FontFamily.Cursive,
//                        color = Color.Gray
//                    )
//                )
//
//            }
//
//            // Right arrow icon
//            Icon(
//                modifier = Modifier
//                    .weight(weight = 1f, fill = false),
////                    imageVector = Icons.Outlined.ChevronRight,
//                imageVector = Icons.Outlined.AccountBox,
//                contentDescription = item.title,
//                tint = Color.Black.copy(alpha = 0.70f)
//            )
//        }
//
//    }
//}
//
//fun prepareOptionsData() {
//
//    val appIcons = Icons.Outlined
//
//    optionsList.add(
//        OptionsData(
//            icon = appIcons.Person,
//            title = "Account",
//            subTitle = "Manage your account"
//        )
//    )
//
//    optionsList.add(
//        OptionsData(
//            icon = appIcons.ShoppingCart,
//            title = "Orders",
//            subTitle = "Orders history"
//        )
//    )
//
//    optionsList.add(
//        OptionsData(
////            icon = appIcons.Navigation,
//            icon = appIcons.Refresh,
//            title = "Addresses",
//            subTitle = "Your saved addresses"
//        )
//    )
//
//    optionsList.add(
//        OptionsData(
////            icon = appIcons.Payments,
//            icon = appIcons.ShoppingCart,
//            title = "Saved Cards",
//            subTitle = "Your saved debit/credit cards"
//        )
//    )
//
//    optionsList.add(
//        OptionsData(
//            icon = appIcons.Settings,
//            title = "Settings",
//            subTitle = "App notification settings"
//        )
//    )
//
//    optionsList.add(
//        OptionsData(
////            icon = appIcons.Help,
//            icon = appIcons.Check,
//            title = "Help Center",
//            subTitle = "FAQs and customer support"
//        )
//    )
//
//    optionsList.add(
//        OptionsData(
////            icon = appIcons.LocalOffer,
//            icon = appIcons.Close,
//            title = "Offers and Coupons",
//            subTitle = "Offers and coupon codes for you"
//        )
//    )
//
//    optionsList.add(
//        OptionsData(
//            icon = appIcons.FavoriteBorder,
//            title = "Wishlist",
//            subTitle = "Items you saved"
//        )
//    )
//}
//
//data class OptionsData(val icon: ImageVector, val title: String, val subTitle: String)