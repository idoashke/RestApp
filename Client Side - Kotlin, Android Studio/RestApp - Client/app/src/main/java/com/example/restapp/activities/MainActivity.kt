package com.example.restapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.restapp.R
import com.example.restapp.databinding.ActivityMainBinding
import com.example.restapp.models.Admin
import com.example.restapp.models.Business
import com.example.restapp.models.Dish
import com.example.restapp.models.User
import com.example.restapp.sql.RetrieveDataTask
import com.example.restapp.ui.theme.LazyColumnTheme
import com.example.restapp.ui.theme.MyTheme
import com.example.restapp.ui.views.MainView

//public val rd = RetrieveDataTask()
//public lateinit var user : User
//public lateinit var admin : Admin
//public lateinit var business : Business
//public lateinit var dishes : MutableList<Dish>

@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme(false) {
                MainView()
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        MainView()
    }
}

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
//        MyApp { }
    }
}

@ExperimentalFoundationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
//        MyApp { }
    }
}
