package com.example.restapp.ui.views

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.restapp.R
import com.example.restapp.routes.Routes
import com.example.restapp.sql.checkAut
import com.example.restapp.sql.retrieveDataFromSql
import com.example.restapp.ui.theme.Purple700

@Composable
fun LoginPage(navController: NavHostController, context: Context = LocalContext.current.applicationContext) {
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Login as admin"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { navController.navigate("AdminLogin") },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val email = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        Spacer(modifier = Modifier.height(80.dp))

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily(Font(R.font.black_ops_one, FontWeight.Normal))))
        // FontFamily(Font(R.font., FontWeight.Normal))

        Spacer(modifier = Modifier.height(40.dp))
        TextField(
            label = { Text(text = "Email") },
            value = email.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email.value = it },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null ) }
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null )}
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    if (checkAut("user", email.value.text, password.value.text) == 1) {
//                        Log.d("MENU" , "Im in menu page")
                        navController.currentBackStackEntry?.savedStateHandle?.set("userEmail", email.value.text)
                        Log.d("MENU" , "Im in menu page")
//                    retrieveDataFromSql("user", "get", email.toString(), "")
                        navController.navigate("Menu")
                    } else {
                        Toast.makeText(context, "Wrong Email or Password...", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("New user? Sign up here"),
            modifier = Modifier
                .padding(20.dp),
            onClick = { navController.navigate(Routes.Signup.route) },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
}

