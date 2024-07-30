package com.example.pinterestclone.di

import com.example.pinterestclone.repositories.PostRepository
import com.example.pinterestclone.services.RetrofitClient
import com.example.pinterestclone.usecases.GetPostsUseCase
import com.example.pinterestclone.viewmodel.DetailViewModel
import com.example.pinterestclone.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitClient.apiService }
    single { PostRepository(get()) }
    single { GetPostsUseCase(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { (id: Int, tags: String?) -> DetailViewModel(get(), id=id, tags=tags) }
}