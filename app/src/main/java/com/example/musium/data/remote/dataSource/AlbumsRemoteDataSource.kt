package com.example.musium.data.remote.dataSource

import com.example.musium.data.remote.ApiResponse
import com.example.musium.data.remote.ApiService
import com.example.musium.data.remote.model.NewReleases
import com.example.musium.data.remote.model.PlaylistTracksResponseDto
import com.example.musium.data.remote.model.SearchPlaylistsResponseDto
import com.example.musium.data.remote.safeApiCall
import jakarta.inject.Inject

interface AlbumsRemoteDataSource {
     suspend fun getNewReleases(limit: Int, offset: Int): ApiResponse<NewReleases>
    suspend fun searchPlaylists(query: String): ApiResponse<SearchPlaylistsResponseDto>
    suspend fun getPlaylistTracks(id: String): ApiResponse<PlaylistTracksResponseDto>
}


class AlbumsRemoteDataSourceImpl @Inject constructor(
    private val api: ApiService
) : AlbumsRemoteDataSource {
    override suspend fun getNewReleases(
        limit: Int,
        offset: Int
    ): ApiResponse<NewReleases> {
        return safeApiCall {
            api.getNewReleases(
                limit = limit,
                offset = offset
            )
        }
    }

    override suspend fun searchPlaylists(query: String): ApiResponse<SearchPlaylistsResponseDto> {
        return safeApiCall {
            api.searchPlaylists(query)
        }
    }

    override suspend fun getPlaylistTracks(id: String): ApiResponse<PlaylistTracksResponseDto> {
        return safeApiCall { api.getPlaylistTracks(id) }
    }
}