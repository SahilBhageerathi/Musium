package com.example.musium.data.mappers

import com.example.musium.data.local.entity.UserEntity
import com.example.musium.data.remote.model.*
import com.example.musium.domain.model.ExplicitContent
import com.example.musium.domain.model.ExternalUrls
import com.example.musium.domain.model.Followers
import com.example.musium.domain.model.User
import com.example.musium.domain.model.UserImage

fun UserEntity.toRemote(): UserDto {
    return UserDto(
        id = id,
        country = country,
        displayName = displayName,
        email = email,
        explicitContent = explicitContent?.let {
            ExplicitContentDto(
                filterEnabled = it.filterEnabled,
                filterLocked = it.filterLocked
            )
        },
        externalUrls = externalUrls?.let {
            ExternalUrlsDto(spotify = it.spotify!!)
        },
        followers = followers?.let {
            FollowersDto(
                href = it.href,
                total = it.total
            )
        },
        href = href,
        images = images?.map {
            UserImageDto(
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


fun UserEntity.toDomain(): User {
    return User(
        id = id,
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
                spotify = it.spotify!!
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