package com.example.musium.data.remote.model


data class PlaylistTracksResponseDto(
    val href: String,
    val items: List<PlaylistTrackItemDto>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)

data class PlaylistTrackItemDto(
    val added_at: String?,
    val added_by: AddedByDto?,
    val is_local: Boolean,
    val primary_color: String?,
    val track: PlayListTrackDto?,
    val video_thumbnail: VideoThumbnailDto?
)

data class AddedByDto(
    val external_urls: PlayListTrackExternalUrlsDto,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
)

data class VideoThumbnailDto(
    val url: String?
)

data class PlayListTrackDto(
    val preview_url: String?,
    val is_playable: Boolean?,
    val explicit: Boolean,
    val type: String,
    val episode: Boolean?,
    val track: Boolean?,
    val album: PlayListTrackAlbumDto,
    val artists: List<PlayListTrackArtistDto>,
    val disc_number: Int,
    val track_number: Int,
    val duration_ms: Int,
    val external_ids: ExternalIdsDto?,
    val external_urls: PlayListTrackExternalUrlsDto,
    val href: String,
    val id: String,
    val name: String,
    val popularity: Int?,
    val uri: String,
    val is_local: Boolean
)

data class ExternalIdsDto(
    val isrc: String?
)


data class PlayListTrackAlbumDto(
    val is_playable: Boolean?,
    val type: String,
    val album_type: String,
    val href: String,
    val id: String,
    val images: List<PlayListTrackImageDto>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val uri: String,
    val artists: List<PlayListTrackArtistDto>,
    val external_urls: PlayListTrackExternalUrlsDto,
    val total_tracks: Int
)

data class PlayListTrackArtistDto(
    val external_urls: PlayListTrackExternalUrlsDto,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)


data class PlayListTrackExternalUrlsDto(
    val spotify: String
)

data class PlayListTrackImageDto(
    val height: Int?,
    val url: String,
    val width: Int?
)
