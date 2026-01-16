package com.example.musium.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Album(
    val albumType: String,
    val totalTracks: Int,
    val availableMarkets: List<String>,
    val externalUrls: AlbumExternalUrls,
    val href: String,
    val id: String,
    val images: List<AlbumImage>,
    val name: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val restrictions: Restrictions?,
    val type: String,
    val uri: String,
    val artists: List<Artist>
) : Parcelable

@Parcelize
@Serializable
data class AlbumExternalUrls(
    val spotify: String
) : Parcelable

@Parcelize
@Serializable
data class Restrictions(
    val reason: String
) : Parcelable

@Parcelize
@Serializable
data class Artist(
    val externalUrls: AlbumExternalUrls,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String

) : Parcelable

@Parcelize
@Serializable
data class AlbumImage(
    val url: String,
    val height: Int,
    val width: Int
) : Parcelable