package com.example.musium.data.mappers

import com.example.musium.data.remote.model.ImageDto
import com.example.musium.data.remote.model.OwnerDto
import com.example.musium.data.remote.model.PlayListExternalUrlsDto
import com.example.musium.data.remote.model.PlaylistDto
import com.example.musium.data.remote.model.TracksDto
import com.example.musium.domain.model.PlayListExternalUrl
import com.example.musium.domain.model.Playlist
import com.example.musium.domain.model.PlaylistImage
import com.example.musium.domain.model.PlaylistOwner
import com.example.musium.domain.model.PlaylistTracks

fun PlaylistDto.toDomain(): Playlist {
    return Playlist(
        id = id,
        name = name,
        description = description,
        collaborative = collaborative,
        owner = owner.toDomain(),
        images = images.map { it.toDomain() },
        tracks = tracks.toDomain(),
        externalUrl = external_urls.toDomain(),
        href = href,
        type = type,
        uri = uri,
        isPublic = public,
        primaryColor = primary_color,
        snapshotId = snapshot_id
    )
}


fun OwnerDto.toDomain(): PlaylistOwner {
    return PlaylistOwner(
        id = id,
        displayName = display_name,
        externalUrl = external_urls.spotify,
        href = href,
        type = type,
        uri = uri
    )
}


fun ImageDto.toDomain(): PlaylistImage {
    return PlaylistImage(
        url = url,
        height = height,
        width = width
    )
}

fun TracksDto.toDomain(): PlaylistTracks {
    return PlaylistTracks(
        href = href,
        total = total
    )
}

fun PlayListExternalUrlsDto.toDomain(): PlayListExternalUrl {
    return PlayListExternalUrl(
        spotify = spotify
    )
}