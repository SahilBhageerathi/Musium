package com.example.musium.data.mappers

import com.example.musium.data.remote.model.AddedByDto
import com.example.musium.data.remote.model.ExternalIdsDto
import com.example.musium.data.remote.model.PlayListTrackAlbumDto
import com.example.musium.data.remote.model.PlayListTrackArtistDto
import com.example.musium.data.remote.model.PlayListTrackDto
import com.example.musium.data.remote.model.PlayListTrackImageDto
import com.example.musium.data.remote.model.PlaylistTrackItemDto
import com.example.musium.domain.model.AddedBy
import com.example.musium.domain.model.ExternalIds
import com.example.musium.domain.model.PlayListTrack
import com.example.musium.domain.model.PlayListTrackAlbum
import com.example.musium.domain.model.PlayListTrackArtist
import com.example.musium.domain.model.PlayListTrackImage
import com.example.musium.domain.model.PlaylistTrackItem

fun PlaylistTrackItemDto.toDomain(): PlaylistTrackItem {
    return PlaylistTrackItem(
        addedAt = added_at,
        addedBy = added_by?.toDomain(),
        isLocal = is_local,
        primaryColor = primary_color,
        track = track?.toDomain(),
        videoThumbnailUrl = video_thumbnail?.url
    )
}

fun AddedByDto.toDomain(): AddedBy {
    return AddedBy(
        id = id,
        href = href,
        uri = uri,
        type = type,
        spotifyUrl = external_urls.spotify
    )
}

fun PlayListTrackDto.toDomain(): PlayListTrack {
    return PlayListTrack(
        id = id,
        name = name,
        durationMs = duration_ms,
        uri = uri,
        href = href,
        spotifyUrl = external_urls.spotify,
        isPlayable = is_playable,
        explicit = explicit,
        previewUrl = preview_url,
        discNumber = disc_number,
        trackNumber = track_number,
        popularity = popularity,
        isLocal = is_local,
        artists = artists.map { it.toDomain() },
        album = album.toDomain(),
        externalIds = external_ids?.toDomain()
    )
}

fun PlayListTrackArtistDto.toDomain(): PlayListTrackArtist {
    return PlayListTrackArtist(
        id = id,
        name = name,
        type = type,
        uri = uri,
        href = href,
        spotifyUrl = external_urls.spotify

    )
}

fun PlayListTrackAlbumDto.toDomain(): PlayListTrackAlbum {
    return PlayListTrackAlbum(
        id = id,
        name = name,
        albumType = album_type,
        releaseDate = release_date,
        releaseDatePrecision = release_date_precision,
        uri = uri,
        href = href,
        spotifyUrl = external_urls.spotify,
        totalTracks = total_tracks,
        isPlayable = is_playable,
        images = images.map { it.toDomain() },
        artists = artists.map { it.toDomain() }
    )
}

fun ExternalIdsDto.toDomain(): ExternalIds {
    return ExternalIds(
        isrc = isrc
    )
}

fun PlayListTrackImageDto.toDomain(): PlayListTrackImage {
    return PlayListTrackImage(
        url = url,
        height = height,
        width = width
    )

}