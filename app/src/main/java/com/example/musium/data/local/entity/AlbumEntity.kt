package com.example.musium.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumEntity(

    @PrimaryKey
    val id: String,

    val albumType: String,
    val totalTracks: Int,
    val availableMarkets: List<String>,
    val externalUrls: AlbumExternalUrlsEntity,
    val href: String,
    val images: List<AlbumImageEntity>,
    val name: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val restrictions: RestrictionsEntity?,
    val type: String,
    val uri: String,
    val artists: List<ArtistEntity>
)


data class AlbumExternalUrlsEntity(
    val spotify: String
)

data class RestrictionsEntity(
    val reason: String
)

data class AlbumImageEntity(
    val url: String,
    val height: Int,
    val width: Int
)

data class ArtistEntity(
    val externalUrls: AlbumExternalUrlsEntity,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)