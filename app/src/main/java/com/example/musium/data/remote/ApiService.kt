package com.example.musium.data.remote

import com.example.musium.data.remote.model.NewReleases
import com.example.musium.data.remote.model.PlaylistTracksResponseDto
import com.example.musium.data.remote.model.SearchPlaylistsResponseDto
//import com.example.musium.data.remote.model.TrackResponseDto
import com.example.musium.data.remote.model.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("me")
    suspend fun getCurrentUserProfile(): Response<UserDto>

    @GET("browse/new-releases")
    suspend fun getNewReleases(
        @Query("market") market: String = "IN",
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<NewReleases>

//    @GET("albums/{id}/tracks")
//    suspend fun getTracks(
//        @Path("id") albumId: String,
//        @Query("offset") offset: Int = 0,
//        @Query("limit") limit: Int = 20
//    ): Response<TrackResponseDto>

    @GET("search")
    suspend fun searchPlaylists(
        @Query("q") query: String,
        @Query("type") type: String = "playlist",
        @Query("market") market: String = "IN",
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): Response<SearchPlaylistsResponseDto>

    @GET("playlists/{playlistId}/tracks")
    suspend fun getPlaylistTracks(
        @Path("playlistId") playlistId: String,
        @Query("market") market: String = "IN",
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): Response<PlaylistTracksResponseDto>
}