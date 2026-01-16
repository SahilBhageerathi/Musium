package com.example.musium.data.remote.model



data class AlbumDto(
    val album_type: String,
    val total_tracks: Int,
    val available_markets: List<String>,
    val external_urls: AlbumExternalUrlsDto,
    val href: String,
    val id: String,
    val images: List<AlbumImageDto>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val restrictions: RestrictionsDto?,
    val type: String,
    val uri: String,
    val artists: List<ArtistDto>
)


data class AlbumExternalUrlsDto(
    val spotify: String
)

data class AlbumImageDto(
    val url: String,
    val height: Int,
    val width: Int
)

data class RestrictionsDto(
    val reason: String
)

data class ArtistDto(
    val external_urls: AlbumExternalUrlsDto,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)
