package com.example.pinterestclone.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pinterestclone.ui.components.Container
import com.example.pinterestclone.ui.components.Gallery
import com.example.pinterestclone.ui.components.Header
import com.example.pinterestclone.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    postViewModel: HomeViewModel,
) {
    val posts by postViewModel.posts.collectAsState()

    Container {
        Header()
        Spacer(modifier = Modifier.height(10.dp))
        Gallery(navController = navController, posts = posts)
    }
}