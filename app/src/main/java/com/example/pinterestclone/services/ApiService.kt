package com.example.pinterestclone.services

import com.example.pinterestclone.model.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("posts.json")
    suspend fun getPosts(@Query("tags") tags: String?): List<Post>

    @GET("posts/{id}.json")
    suspend fun findPosts(@Path("id") id: Int): Post
}