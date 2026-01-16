package com.example.musium.data.mappers

import com.example.musium.data.local.entity.*
import com.example.musium.data.remote.model.UserDto
import com.example.musium.domain.model.*

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        id = id!!,
        country = country,
        displayName = displayName,
        email = email,
        explicitContent = explicitContent?.let {
            ExplicitContentEntity(
                filterEnabled = it.filterEnabled,
                filterLocked = it.filterLocked
            )
        },
        externalUrls = externalUrls?.let {
            ExternalUrlsEntity(spotify = it.spotify)
        },
        followers = followers?.let {
            FollowersEntity(
                href = it.href,
                total = it.total
            )
        },
        href = href,
        images = images?.map {
            UserImageEntity(
                url = it.url,
                height = it.height,
                width = it.width
            )
        },
        product = product,
        type = type,
        uri = uri
    )
}


fun UserDto.toDomain(): User {
    return User(
        id = id!!,
        country = country,
        displayName = displayName,
        email = email,
        explicitContent = explicitContent?.let {
            ExplicitContent(
                filterEnabled = it.filterEnabled,
                filterLocked = it.filterLocked
            )
        },
        externalUrls = externalUrls?.let {
            ExternalUrls(
                spotify = it.spotify
            )
        },
        followers = followers?.let {
            Followers(
                href = it.href,
                total = it.total
            )
        },
        href = href,
        images = images?.map {
            UserImage(
                url = it.url,
                height = it.height,
                width = it.width
            )
        },
        product = product,
        type = type,
        uri = uri
    )
}
