package com.example.pinterestclone.model

data class Post(
    val id: Int,
    val source: String,
    val created_at: String,
    val file_url: String,
    val large_file_url: String,
    val preview_file_url: String,
    val tag_string_character: String,
    val tag_string_copyright: String,
    val tag_string: String,
)
