package com.example.pinterestclone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pinterestclone.model.Post
import com.example.pinterestclone.utils.truncate

@Composable
fun Gallery(navController: NavController, posts: MutableList<Post>, mainContent: @Composable (() -> Unit)? = null) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        verticalItemSpacing = 14.dp,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        content = {
            if(mainContent != null) {
                item(span=StaggeredGridItemSpan.FullLine) { mainContent() }
            }

            items(posts.size) { i ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        val id = posts[i].id

                        val chTags = posts[i].tag_string_character.split(" ")[0]
                        val coTags = posts[i].tag_string_copyright.split(" ")[0]
                        val reTags = posts[i].tag_string.split(" ")[0]

                        val tags = (if (chTags.isNotEmpty()) chTags else if (coTags.isNotEmpty()) coTags else reTags) ?: null

                        navController.navigate("detail?id=$id&tags=$tags")
                    }
                ) {
                    AsyncImage(
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        model = posts[i].preview_file_url,
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clip(RoundedCornerShape(8.dp))
                    )
                    if (posts[i].tag_string_character.isNotEmpty()) Text(text = truncate(posts[i].tag_string_character), textAlign = TextAlign.Center, fontSize = 13.sp)
                    else if (posts[i].tag_string_copyright.isNotEmpty()) Text(text = truncate(posts[i].tag_string_copyright, 10), textAlign = TextAlign.Center, fontSize = 13.sp)
                }
            }
        },
    )
}