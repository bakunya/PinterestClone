package com.example.pinterestclone.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pinterestclone.model.Post
import com.example.pinterestclone.usecases.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val getPostsUseCase: GetPostsUseCase, id: Int, tags: String?): ViewModel() {
    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> = _post

    private val _related = MutableStateFlow<MutableList<Post>>(mutableListOf())
    val related: StateFlow<MutableList<Post>> = _related

    fun findPost(id: Int) {
        viewModelScope.launch {
            _post.value = getPostsUseCase.find(id)
        }
    }

    fun loadPosts(tags: String?) {
        viewModelScope.launch {
            _related.value = getPostsUseCase.getAll(tags).toMutableStateList()
        }
    }
}
