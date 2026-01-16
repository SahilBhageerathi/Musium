package com.example.musium.data.repo

import com.example.musium.data.mappers.toDomain
import com.example.musium.data.remote.ApiResponse
import com.example.musium.data.remote.dataSource.AlbumsRemoteDataSource
import com.example.musium.domain.model.Album
import com.example.musium.domain.model.Playlist
import com.example.musium.domain.model.PlaylistTrackItem
import com.example.musium.domain.repo.SongsRepository

class SongsRepositoryImpl(
    private val remote: AlbumsRemoteDataSource
) : SongsRepository {
    override suspend fun fetchAlbums(): List<Album> {

        when (val response = remote.getNewReleases()) {
            is ApiResponse.Success -> {
                val remoteAlbums = response.data

                return remoteAlbums.albums.items.let {
                    it.map { album -> album.toDomain() }
                }
            }

            is ApiResponse.Error -> {
                throw Exception(response.message)
            }

        }
    }

    override suspend fun fetchTop50TeluguHindiPlaylist(query: String): List<Playlist> {
        when(val response = remote.searchPlaylists(query)){
            is ApiResponse.Success -> {
                return response.data.playlists.items.mapNotNull { it?.toDomain() }
            }

            is ApiResponse.Error -> {
                throw Exception(response.message)
            }
        }
    }

    override suspend fun loadMore(page: Int): List<Album> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlaylistTracks(playlistId: String): List<PlaylistTrackItem> {
        when(val response = remote.getPlaylistTracks(playlistId)){
            is ApiResponse.Success -> {
                return response.data.items.map{ it.toDomain()}
            }

            is ApiResponse.Error -> {
                throw Exception(response.message)
            }
        }
    }


}