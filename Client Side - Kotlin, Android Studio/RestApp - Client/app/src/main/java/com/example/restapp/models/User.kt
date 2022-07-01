package com.example.restapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var email: String,
    var firstName: String,
    var lastName: String,
    var dateOfBirth: String,
//    var gender: String,
    var city: String,
    var address: String,
    var phoneNum: String,
    var password: String
): Parcelable
