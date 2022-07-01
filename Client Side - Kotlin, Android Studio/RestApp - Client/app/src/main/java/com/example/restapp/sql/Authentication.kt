package com.example.restapp.sql

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue


fun checkAut(obj : String, email: String, password: String) : Number {
//    Log.d("email is ", email )
//    Log.d("password is ", password )
    val rd = RetrieveDataTask()
    var data : String = ""
    if (obj.equals("user")){
        data = rd.execute("pullUser", email).get()
    } else if (obj.equals("admin")){
        data = rd.execute("pullAdmin", email).get()
    }
    if (data.equals("failed")){
        return 0;
    }
    return checkEmailAndPassword(obj, data, email, password)

}


fun checkEmailAndPassword(obj : String, data: String,email : String, password : String) : Number {
    var emailFromData : String = ""
    var passwordFromData : String = ""
    Log.d("Auth user data is " , data)
    val dataList = data.split("~") // [0 , 1 , 2 , ... ]
    if (obj.equals("user")){
        emailFromData = dataList[0]
        passwordFromData = dataList[7]
    } else if (obj.equals("admin")){
        emailFromData = dataList[0]
        passwordFromData = dataList[3]
    }

    Log.d("passwordFromData is " , passwordFromData)


    if (emailFromData.equals(email) && passwordFromData.equals(password))
        return 1

    return 0
}
