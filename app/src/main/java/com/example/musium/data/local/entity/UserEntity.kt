package com.example.musium.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: String,

    val country: String?,
    val displayName: String?,
    val email: String?,

    val explicitContent: ExplicitContentEntity?,
    val externalUrls: ExternalUrlsEntity?,
    val followers: FollowersEntity?,

    val href: String?,
    val images: List<UserImageEntity>?,
    val product: String?,
    val type: String?,
    val uri: String?
)

data class ExplicitContentEntity(
    val filterEnabled: Boolean?,
    val filterLocked: Boolean?
)

data class ExternalUrlsEntity(
    val spotify: String?
)

data class FollowersEntity(
    val href: String?,
    val total: Int?
)
data class UserImageEntity(
    val url: String?,
    val height: Int?,
    val width: Int?
)
