package com.example.pinterestclone.utils

import com.google.gson.Gson

fun <T> prettyPrint(param: T) {
    val g = Gson()
    val jsonString = g.toJson(param)
    val gson = com.google.gson.GsonBuilder().setPrettyPrinting().create()
    val jsonObject = gson.fromJson(jsonString, Any::class.java)
    println(gson.toJson(jsonObject))
}