package com.example.restapp.models

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Dish(
    var id : Long,
    var name : String,
    var category : String,
    var description : String,
    var price : Int,
//    var image : String,
//    var category : String,
//    var dishImageId: Int = 0
    var dishImageId: String

): Parcelable

