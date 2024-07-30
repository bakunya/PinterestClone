package com.example.pinterestclone.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Container(content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        content()
    }
}