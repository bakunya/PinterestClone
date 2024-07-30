package com.example.pinterestclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pinterestclone.repositories.PostRepository
import com.example.pinterestclone.services.ApiService
import com.example.pinterestclone.services.RetrofitClient
import com.example.pinterestclone.ui.screens.DetailScreen
import com.example.pinterestclone.ui.screens.HomeScreen
import com.example.pinterestclone.ui.theme.PinterestCloneTheme
import com.example.pinterestclone.usecases.GetPostsUseCase
import com.example.pinterestclone.viewmodel.DetailViewModel
import com.example.pinterestclone.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PinterestCloneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                val postViewModel: HomeViewModel = getViewModel()
                                HomeScreen(navController = navController, postViewModel = postViewModel)
                            }
                            composable("detail?id={id}&tags={tags}") { backStackEntry ->
                                val id = backStackEntry.arguments?.getString("id")!!
                                val tags = backStackEntry.arguments?.getString("tags") ?: ""
                                val detailViewModel: DetailViewModel = getViewModel { parametersOf(id.toInt(), tags) }

                                DetailScreen(
                                    id = id,
                                    tags = tags,
                                    navController = navController,
                                    detailViewModel = detailViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}