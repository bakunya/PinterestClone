package com.example.pinterestclone.usecases

import com.example.pinterestclone.model.Post
import com.example.pinterestclone.repositories.PostRepository

class GetPostsUseCase(private val postRepository: PostRepository) {
    suspend fun getAll(tags: String? = null): List<Post> {
        return postRepository.getPosts(tags)
    }

    suspend fun find(id: Int): Post {
        return postRepository.findPosts(id)
    }
}