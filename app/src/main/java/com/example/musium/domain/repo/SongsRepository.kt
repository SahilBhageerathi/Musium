package com.example.musium.domain.repo

import com.example.musium.domain.model.Album
import com.example.musium.domain.model.Playlist
import com.example.musium.domain.model.PlaylistTrackItem

interface SongsRepository {
    suspend fun fetchAlbums(): List<Album>

    suspend fun fetchTop50TeluguHindiPlaylist(query:String): List<Playlist>
    suspend fun loadMore(page: Int): List<Album>

    suspend fun getPlaylistTracks(playlistId: String): List<PlaylistTrackItem>

}

