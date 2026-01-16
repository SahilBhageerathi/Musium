package com.example.musium.data.remote.model

data class NewReleases(
    val albums: Albums
)

data class Albums(
    val href: String,
    val items: List<AlbumDto>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)