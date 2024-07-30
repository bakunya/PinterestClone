package com.example.pinterestclone.repositories

import com.example.pinterestclone.model.Post
import com.example.pinterestclone.services.ApiService

class PostRepository(private val apiService: ApiService) {
    suspend fun getPosts(tags: String?): List<Post> {
        return apiService.getPosts(tags)
    }

    suspend fun findPosts(id: Int): Post {
        return apiService.findPosts(id)
    }
}