package com.example.musium.data.mappers

import com.example.musium.data.remote.model.AlbumDto
import com.example.musium.data.remote.model.AlbumExternalUrlsDto
import com.example.musium.data.remote.model.AlbumImageDto
import com.example.musium.data.remote.model.ArtistDto
import com.example.musium.data.remote.model.RestrictionsDto
import com.example.musium.domain.model.Album
import com.example.musium.domain.model.AlbumExternalUrls
import com.example.musium.domain.model.AlbumImage
import com.example.musium.domain.model.Artist
import com.example.musium.domain.model.Restrictions

//fun AlbumDto.toEntity(): AlbumEntity {
//    return AlbumEntity(
//        id = id,
//        albumType = album_type,
//        totalTracks = total_tracks,
//        availableMarkets = available_markets,
//        externalUrls = external_urls.toEntity(),
//        href = href,
//        images = images.map { it.toEntity() },
//        name = name,
//        releaseDate = release_date,
//        releaseDatePrecision = release_date_precision,
//        restrictions = restrictions?.toEntity(),
//        type = type,
//        uri = uri,
//        artists = artists.map { it.toEntity() }
//    )
//}


fun AlbumDto.toDomain(): Album {
    return Album(
        id = id,
        name = name,
        type = album_type,
        totalTracks = total_tracks,
        releaseDate = release_date,
        releaseDatePrecision = release_date_precision,
        images = images.map { it.toDomain() },
        artists = artists.map { it.toDomain() },
        uri = uri,
        availableMarkets = available_markets,
        albumType = album_type,
        externalUrls = external_urls.toDomain(),
        href = href,
        restrictions = restrictions?.toDomain(),
    )
}

//fun ExternalUrlsDto.toEntity(): ExternalUrlsEntity {
//    return ExternalUrlsEntity(
//        spotify = spotify
//    )
//}

fun AlbumExternalUrlsDto.toDomain(): AlbumExternalUrls {
    return AlbumExternalUrls(
        spotify = spotify
    )
}

//fun AlbumImageDto.toEntity(): AlbumImageEntity {
//    return AlbumImageEntity(
//        url = url,
//        height = height,
//        width = width
//    )
//}

fun AlbumImageDto.toDomain(): AlbumImage {
    return AlbumImage(
        url = url,
        height = height,
        width = width
    )
}

//fun RestrictionsDto.toEntity(): RestrictionsEntity {
//    return RestrictionsEntity(reason = reason)
//}

fun RestrictionsDto.toDomain(): Restrictions {
    return Restrictions(reason = reason)
}

fun ArtistDto.toDomain(): Artist {
    return Artist(
        id = id,
        name = name,
        externalUrls = external_urls.toDomain(),
        href = href,
        type = type,
        uri = uri,
    )
}

//fun ArtistDto.toEntity(): ArtistEntity {
//    return ArtistEntity(
//        id = id,
//        name = name,
//        externalUrls = external_urls.toEntity(),
//        href = href,
//        type = type,
//        uri = uri,
//    )
//}

