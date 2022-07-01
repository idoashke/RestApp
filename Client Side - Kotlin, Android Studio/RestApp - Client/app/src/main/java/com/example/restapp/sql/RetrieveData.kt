package com.example.restapp.sql

import android.util.Log
import androidx.compose.runtime.remember
import com.example.restapp.activities.*
import com.example.restapp.models.Admin
import com.example.restapp.models.Business
import com.example.restapp.models.Dish
import com.example.restapp.models.User


fun retrieveDataFromSql(obj : String, action : String, key : String, objData : String) : String{

    var result : String = ""
    val rd = RetrieveDataTask()
    result = when (obj) {
        "user" -> userActions(rd, action, key, objData)
        "admin" -> adminActions(rd, action, key, objData)
        "business" -> businessActions(rd, action, key, objData)
        "dish" -> dishActions(rd, action, key, objData)
        else -> {
            "failed"
        }
    }

    return result

}

fun userActions(rd : RetrieveDataTask ,action: String, key : String, userData : String): String {
    var answer  = "failed"
    when (action) {
        "insert" -> {
            answer = rd.execute("insertUser", userData).get()

        }
        "get" -> {
            answer = rd.execute("pullUser", key).get()


        }
        "update" -> { //updateUser,oldemail,email,fisrtname,lastname,date,city,address,phonenum,password
            var str : String = key + "," + userData
            answer = rd.execute("updateUser", str).get()
        }
        "delete" -> {
            answer = rd.execute("deleteUser", key).get()
        }
        else -> {
            "failed"
        }
    }
    return answer
}


fun adminActions(rd : RetrieveDataTask, action: String, key : String, adminData : String): String {
    var answer  = "failed"
    when (action) {
        "insert" -> {
            answer = rd.execute("insertAdmin", adminData).get()

        }
        "get" -> {
            answer = rd.execute("pullAdmin", key).get()
        }
        "update" -> {
            var str : String = key + "," + adminData
            answer = rd.execute("updateAdmin", str).get()
        }
        "delete" -> {
            rd.execute("deleteAdmin", key).get()
        }
        else -> {
            "failed"
        }
    }
    return answer
}


fun businessActions(rd : RetrieveDataTask, action: String, key : String, businessData : String): String {
    var answer  = "failed"
    when (action) {
        "get" -> {
            answer = rd.execute("pullBusiness", key).get()
        }
        "update" -> {
            var str : String = key + "," + businessData
            answer = rd.execute("updateBusiness", str).get()
        }
        "delete" -> {
            rd.execute("deleteBusiness", key).get()
        }
        else -> {
            "failed"
        }
    }
    return answer
}


fun dishActions(rd : RetrieveDataTask, action: String, key : String, dishData : String): String {
    var answer  = "failed"
    when (action) {
        "insert" -> {
            answer = rd.execute("insertDish", dishData).get()

        }
        "get" -> {
            answer = rd.execute("pullDish" , "").get()
        }
        "update" -> {
            var str : String = key + "," + dishData
            answer = rd.execute("updateDish", str).get()
        }
        "delete" -> {
            rd.execute("deleteDish", key).get()
        }
        else -> {
            "failed"
        }
    }

    return answer

}

