package com.example.pinterestclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pinterestclone.model.Post
import com.example.pinterestclone.usecases.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getPostsUseCase: GetPostsUseCase): ViewModel() {
    private val _posts = MutableStateFlow<MutableList<Post>>(mutableListOf())
    val posts: StateFlow<MutableList<Post>> = _posts

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            val posts1 = getPostsUseCase.getAll()
            _posts.value = posts1.toMutableList()
        }
    }
}