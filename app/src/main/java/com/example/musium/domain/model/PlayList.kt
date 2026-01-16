package com.example.musium.domain.model


data class Playlist(
    val id: String,
    val name: String,
    val description: String?,
    val collaborative: Boolean,
    val owner: PlaylistOwner,
    val images: List<PlaylistImage>,
    val tracks: PlaylistTracks,
    val externalUrl: PlayListExternalUrl,
    val href: String,
    val type: String,
    val uri: String,
    val isPublic: Boolean?,
    val primaryColor: String?,
    val snapshotId: String
)

data class PlaylistOwner(
    val id: String,
    val displayName: String?,
    val externalUrl: String,
    val href: String,
    val type: String,
    val uri: String
)

data class PlaylistImage(
    val url: String,
    val height: Int?,
    val width: Int?
)

data class PlaylistTracks(
    val href: String,
    val total: Int
)

data class PlayListExternalUrl(
    val spotify: String
)
