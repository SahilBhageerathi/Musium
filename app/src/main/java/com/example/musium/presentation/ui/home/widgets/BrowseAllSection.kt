package com.example.musium.presentation.ui.home.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.example.musium.domain.model.Album
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer


fun LazyListScope.browseAllAlbumsSection(
    albums: LazyPagingItems<Album>,
    onAlbumClick: (Album) -> Unit
) {
    val albumRows = (albums.itemCount + 1) / 2

    items(
        count = albumRows,
        key = { rowIndex ->
            val leftIndex = rowIndex * 2
            albums.peek(leftIndex)?.id ?: rowIndex
        }
    ) { rowIndex ->

        val leftIndex = rowIndex * 2
        val rightIndex = leftIndex + 1

        val leftAlbum = albums[leftIndex]
        val rightAlbum = if (rightIndex < albums.itemCount) albums[rightIndex] else null

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (leftAlbum != null) {
                BrowseAllAlbumItem(
                    album = leftAlbum,
                    onItemClick = { onAlbumClick(leftAlbum) },
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            if (rightAlbum != null) {
                BrowseAllAlbumItem(
                    album = rightAlbum,
                    onItemClick = { onAlbumClick(rightAlbum) },
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }

    albums.itemCount

//    for( i in 0 until albums.itemCount) {
//        Log.d("ALBUM Length", albums[i]?.name ?: "Empty")
//    }
//    Log.d("ALBUM Length","#########################################################################################################")


//    item {
//        when (val append = albums.loadState.append) {
//            is LoadState.Loading -> Text("Loading more...", color = Color.White)
//
//            is LoadState.Error -> Button(onClick = { albums.retry() }) {
//                Text("Retry loading more")
//            }
//
//            else -> Unit
//        }
//    }
}


@Composable
fun BrowseAllAlbumItem(album: Album, onItemClick: () -> Unit,modifier: Modifier) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    var isImageLoaded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(4.dp)
            .height(180.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick()
            },
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = album.images.firstOrNull()?.url,
                contentDescription = album.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmer(shimmer)
                            .background(Color.Gray.copy(alpha = 0.4f))
                    )
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Failed to load", color = Color.White)
                    }
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                        )
                    )
            )
            Text(
                text = album.name,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}