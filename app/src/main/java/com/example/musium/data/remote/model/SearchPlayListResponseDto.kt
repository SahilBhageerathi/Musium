package com.example.musium.data.remote.model

data class SearchPlaylistsResponseDto(
    val playlists: PlaylistsDto
)

data class PlaylistsDto(
    val href: String,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int,
    val items: List<PlaylistDto?>
)

data class PlaylistDto(
    val collaborative: Boolean,
    val description: String?,
    val external_urls: PlayListExternalUrlsDto,
    val href: String,
    val id: String,
    val images: List<ImageDto>,
    val name: String,
    val owner: OwnerDto,
    val primary_color: String?,
    val public: Boolean?,
    val snapshot_id: String,
    val tracks: TracksDto,
    val type: String,
    val uri: String
)

data class PlayListExternalUrlsDto(
    val spotify: String
)

data class ImageDto(
    val height: Int?,
    val url: String,
    val width: Int?
)

data class OwnerDto(
    val display_name: String?,
    val external_urls: PlayListExternalUrlsDto,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
)

data class TracksDto(
    val href: String,
    val total: Int
)

