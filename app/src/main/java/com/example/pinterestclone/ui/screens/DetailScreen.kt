package com.example.pinterestclone.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pinterestclone.ui.components.Container
import com.example.pinterestclone.ui.components.Gallery
import com.example.pinterestclone.ui.components.Header
import com.example.pinterestclone.utils.truncate
import com.example.pinterestclone.viewmodel.DetailViewModel

@Composable
fun DetailScreen(id: String, tags: String, navController: NavController, detailViewModel: DetailViewModel) {
    val context = LocalContext.current
    val post by detailViewModel.post.collectAsState()
    val related by detailViewModel.related.collectAsState()

    LaunchedEffect(key1 = id) {
        detailViewModel.findPost(id = id.toInt())
    }

    LaunchedEffect(key1 = tags) {
        detailViewModel.loadPosts(tags = tags)
    }

    Container {
        Header()
        Spacer(modifier = Modifier.height(10.dp))
        Gallery(navController = navController, posts = related, mainContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (post != null) {
                    AsyncImage(
                        model = post!!.large_file_url,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.height(13.dp))
                    if (post!!.tag_string_character.isNotEmpty()) Text(text = post!!.tag_string_character, textAlign = TextAlign.Center, fontSize = 16.sp)
                    else if (post!!.tag_string_copyright.isNotEmpty()) Text(text = post!!.tag_string_copyright, textAlign = TextAlign.Center, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            val uri = Uri.parse(post!!.file_url)
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            context.startActivity(intent)
                        },
                        colors = ButtonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Red,
                            disabledContentColor = Color.White,
                        )
                    ) {
                        Text(text = "Download")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        })
    }
}