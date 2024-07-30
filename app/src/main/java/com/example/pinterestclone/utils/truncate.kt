package com.example.pinterestclone.utils

fun truncate(text: String, length: Int = 15): String {
    if(text.length <= length) return text
    return text.substring(0, length) + "..."
}