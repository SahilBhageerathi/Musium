package com.example.musium.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.musium.R
import com.example.musium.domain.model.Album
import com.example.musium.domain.model.Playlist
import com.example.musium.domain.model.User
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPlayListClick: (Playlist) -> Unit
) {

    val state = viewModel.homeState.collectAsStateWithLifecycle().value

    Scaffold {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.0f),
                            Color.Transparent
                        )
                    )
                )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            item {
                HomeHeader(state.currentUser)
            }

            item {
                continueListeningSection(state.albums)
            }

            item {
                Text(
                    text = "Latest Trending",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }

            item {
                TrendingSection(state.topPlayList, onItemClick = { playList ->
                    onPlayListClick(playList)
                })
            }

            item {
                Text(
                    text = "Browse All",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }

            item {
                AlbumSection(state.albums)
            }
        }
    }

}


@Composable
fun HomeHeader(user: User?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        val imageUrl = user?.images?.firstOrNull()?.url

        if (imageUrl.isNullOrEmpty()) {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp)
            )
        } else {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))
        Column {
            Text(
                "Welcome Back!",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                user?.displayName ?: "Guest",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium,
            )
        }

    }
}

@Composable
fun continueListeningSection(albums: List<Album>) {
    val visibleAlbums = albums.take(6)
    Column {
        Text("Continue Listening = ${albums.size}", color = Color.White)
        Spacer(modifier = Modifier.size(10.dp))
        LazyVerticalGrid(
            modifier = Modifier.height((60.dp * 3) + (8.dp * 2)),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            userScrollEnabled = false
        ) {
            items(
                items = visibleAlbums,
                key = { visibleAlbums -> visibleAlbums.id }
            ) { album ->
                continueListeningItem(
                    album = album,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surface),
                    {})
            }
        }
    }

}

@Composable
fun continueListeningItem(album: Album, modifier: Modifier, onItemClick: () -> Unit) {
    Row(
        modifier = modifier
            .clickable { onItemClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = album.images[1].url,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .width(60.dp)

        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = album.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun TrendingSection(albums: List<Playlist>, onItemClick: (Playlist) -> Unit) {
    LazyHorizontalGrid(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        rows = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        items(
            items = albums,
            key = { albums -> albums.id }
        ) { album ->
            TrendingItem(album, onItemClick = { onItemClick(album) })
        }
    }
}

@Composable
fun TrendingItem(playlist: Playlist, onItemClick: (Playlist) -> Unit) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    var isImageLoaded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()
            .width(150.dp)
            .clickable {
                onItemClick(playlist)
            },
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = playlist.images.firstOrNull()?.url,
                contentDescription = playlist.name,
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
                text = playlist.name,
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

@Composable
fun AlbumSection(albums: List<Album>) {

    val rows = (albums.size + 1) / 2
    val itemHeight = 180.dp
    val spacing = 8.dp
    val totalHeight = (rows * itemHeight) + ((rows - 1) * spacing)

    LazyVerticalGrid(
        modifier = Modifier.height(totalHeight),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false
    ) {
        items(
            items = albums,
            key = { albums -> albums.id }
        ) { album ->
            AlbumItem(
                album = album,
                {})
        }
    }
}

@Composable
fun AlbumItem(album: Album, onItemClick: () -> Unit) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    var isImageLoaded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
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
