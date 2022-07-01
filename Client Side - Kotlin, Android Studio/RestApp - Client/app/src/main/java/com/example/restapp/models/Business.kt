package com.example.restapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Business(
    var name : String,
    var city : String,
    var address : String,
//    var restDishes : List<Dish>

): Parcelable
