package com.example.restapp.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.restapp.R
import com.example.restapp.component.CustomTopAppBar
import com.example.restapp.sql.retrieveDataFromSql
import com.google.android.material.textfield.TextInputEditText

@Composable
fun SignUp(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBar(navController)

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldWithTopBar(navController: NavHostController, context: Context = LocalContext.current.applicationContext) {
    val scrollState = rememberScrollState()

    Scaffold(
//        topBar = {
//            CustomTopAppBar(navController, "Signup", true)
//        },
        content = {
            Column(
                modifier = Modifier.padding(20.dp).verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                val email = remember { mutableStateOf(TextFieldValue()) }
                val fName = remember { mutableStateOf(TextFieldValue()) }
                val lName = remember { mutableStateOf(TextFieldValue()) }
                val bDate = remember { mutableStateOf(TextFieldValue()) }
                val city = remember { mutableStateOf(TextFieldValue()) }
                val address = remember { mutableStateOf(TextFieldValue()) }
                val phone = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }

                Text(text = "SignUp", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily(
                    Font(R.font.black_ops_one, FontWeight.Normal)
                )))

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Email") },
                    value = email.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = { email.value = it },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null )}
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "First Name") },
                    value = fName.value,
                    onValueChange = { fName.value = it },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null )}
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Last Name") },
                    value = lName.value,
                    onValueChange = { lName.value = it },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null )}
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Birth Date") },
                    value = bDate.value,
                    onValueChange = { bDate.value = it },
                    leadingIcon = { Icon(Icons.Filled.DateRange, contentDescription = null )}
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "City") },
                    value = city.value,
                    onValueChange = { city.value = it },
                    leadingIcon = { Icon(Icons.Filled.Place, contentDescription = null )}
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Address") },
                    value = address.value,
                    onValueChange = { address.value = it },
                    leadingIcon = { Icon(Icons.Filled.Home, contentDescription = null )}
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Phone") },
                    value = phone.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    onValueChange = { phone.value = it },
                    leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null )}
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
                            var finalEmail = email.value.text
                            var finalFName = fName.value.text
                            var finalLName = lName.value.text
                            var finalBDate = bDate.value.text
                            var finalCity = city.value.text
                            var finalAddress = address.value.text
                            var finalPhone = phone.value.text
                            var finalPassword = password.value.text
                            if (!finalEmail.isEmpty() && !finalFName.isEmpty() && !finalLName.isEmpty()
                                && !finalBDate.isEmpty() && !finalCity.isEmpty() && !finalAddress.isEmpty()
                                && !finalPhone.isEmpty() && !finalPassword.isEmpty()) {
                                var userStr =
                                    finalEmail + "," + finalFName + "," + finalLName + "," +
                                            finalBDate + "," + finalCity + "," + finalAddress + "," +
                                            finalPhone + "," + finalPassword
                                var answer = retrieveDataFromSql("user", "insert", "", userStr)
                                if (answer.equals("success")){
                                    Toast.makeText(context, "User created successfully!", Toast.LENGTH_SHORT).show()
                                    navController.navigate("Login")

                                } else {
                                    Toast.makeText(context, "Failed to add user..", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(context, "Please fill all the fields!!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "SignUp")
                    }
                }

            }
//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Sign up",
//                    fontSize = 30.sp,
//                    color = Color.Black
//                )
//            }

        })
}



