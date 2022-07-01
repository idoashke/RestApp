package com.example.restapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Admin(
    var email : String,
    var firstName : String,
    var lastName : String,
    var password : String

): Parcelable
