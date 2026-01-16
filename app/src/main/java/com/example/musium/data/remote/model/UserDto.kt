package com.example.musium.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("country")
    val country: String?,

    @SerializedName("display_name")
    val displayName: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("explicit_content")
    val explicitContent: ExplicitContentDto?,

    @SerializedName("external_urls")
    val externalUrls: ExternalUrlsDto?,

    @SerializedName("followers")
    val followers: FollowersDto?,

    @SerializedName("href")
    val href: String?,

    @SerializedName("id")
    val id: String?,

    @SerializedName("images")
    val images: List<UserImageDto>?,

    @SerializedName("product")
    val product: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("uri")
    val uri: String?
)

data class ExplicitContentDto(
    @SerializedName("filter_enabled")
    val filterEnabled: Boolean?,

    @SerializedName("filter_locked")
    val filterLocked: Boolean?
)

data class ExternalUrlsDto(
    @SerializedName("spotify")
    val spotify: String?
)

data class FollowersDto(
    @SerializedName("href")
    val href: String?,

    @SerializedName("total")
    val total: Int?
)

data class UserImageDto(
    @SerializedName("url")
    val url: String?,

    @SerializedName("height")
    val height: Int?,

    @SerializedName("width")
    val width: Int?
)
