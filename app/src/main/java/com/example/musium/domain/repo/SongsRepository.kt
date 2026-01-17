package com.example.musium.domain.repo

import androidx.paging.PagingData
import com.example.musium.domain.model.Album
import com.example.musium.domain.model.Playlist
import com.example.musium.domain.model.PlaylistTrackItem
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
     fun getNewReleasedAlbums(): Flow<PagingData<Album>>

    suspend fun fetchTop50TeluguHindiPlaylist(query: String): List<Playlist>
    suspend fun loadMore(page: Int): List<Album>

    suspend fun getPlaylistTracks(playlistId: String): List<PlaylistTrackItem>

}

