package com.example.musium.domain.model

data class PlaylistTrackItem(
    val addedAt: String?,
    val addedBy: AddedBy?,
    val isLocal: Boolean,
    val primaryColor: String?,
    val track: PlayListTrack?,
    val videoThumbnailUrl: String?
)


data class AddedBy(
    val id: String,
    val href: String,
    val uri: String,
    val type: String,
    val spotifyUrl: String
)

data class PlayListTrack(
    val id: String,
    val name: String,
    val durationMs: Int,
    val uri: String,
    val href: String,
    val spotifyUrl: String,
    val isPlayable: Boolean?,
    val explicit: Boolean,
    val previewUrl: String?,
    val discNumber: Int,
    val trackNumber: Int,
    val popularity: Int?,
    val isLocal: Boolean,
    val artists: List<PlayListTrackArtist>,
    val album: PlayListTrackAlbum?,
    val externalIds: ExternalIds?
)


data class PlayListTrackAlbum(
    val id: String,
    val name: String,
    val albumType: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val uri: String,
    val href: String,
    val spotifyUrl: String,
    val totalTracks: Int,
    val isPlayable: Boolean?,
    val images: List<PlayListTrackImage>,
    val artists: List<PlayListTrackArtist>
)

data class PlayListTrackArtist(
    val id: String,
    val name: String,
    val type: String,
    val uri: String,
    val href: String,
    val spotifyUrl: String
)

data class ExternalIds(
    val isrc: String?
)

data class PlayListTrackImage(
    val url: String,
    val height: Int?,
    val width: Int?
)


