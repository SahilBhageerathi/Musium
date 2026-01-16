package com.example.musium.domain.model


data class User(
    val id: String,

    val country: String?,
    val displayName: String?,
    val email: String?,

    val explicitContent: ExplicitContent?,
    val externalUrls: ExternalUrls?,
    val followers: Followers?,

    val href: String?,
    val images: List<UserImage>?,
    val product: String?,
    val type: String?,
    val uri: String?
)

data class ExplicitContent(
    val filterEnabled: Boolean?,
    val filterLocked: Boolean?
)

data class ExternalUrls(
    val spotify: String?
)

data class Followers(
    val href: String?,
    val total: Int?
)

data class UserImage(
    val url: String?,
    val height: Int?,
    val width: Int?
)
