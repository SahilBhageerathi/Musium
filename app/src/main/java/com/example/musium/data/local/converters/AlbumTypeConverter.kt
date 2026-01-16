package com.example.musium.data.local.converters

import androidx.room.TypeConverter
import com.example.musium.data.local.entity.AlbumImageEntity
import com.example.musium.data.local.entity.ArtistEntity
import com.example.musium.data.local.entity.RestrictionsEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.jvm.java

import com.example.musium.data.local.entity.AlbumExternalUrlsEntity


class AlbumConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value ?: emptyList<String>())
    }

    @TypeConverter
    fun toStringList(value: String?): List<String> {
        if (value.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }


    @TypeConverter
    fun fromExternalUrls(value: AlbumExternalUrlsEntity?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toExternalUrls(value: String?): AlbumExternalUrlsEntity? {
        return value?.let { gson.fromJson(it, AlbumExternalUrlsEntity::class.java) }
    }

    @TypeConverter
    fun fromRestrictions(value: RestrictionsEntity?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toRestrictions(value: String?): RestrictionsEntity? {
        return value?.let { gson.fromJson(it, RestrictionsEntity::class.java) }
    }


    @TypeConverter
    fun fromAlbumImages(value: List<AlbumImageEntity>?): String {
        return gson.toJson(value ?: emptyList<AlbumImageEntity>())
    }

    @TypeConverter
    fun toAlbumImages(value: String?): List<AlbumImageEntity> {
        if (value.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<AlbumImageEntity>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromArtists(value: List<ArtistEntity>?): String {
        return gson.toJson(value ?: emptyList<ArtistEntity>())
    }

    @TypeConverter
    fun toArtists(value: String?): List<ArtistEntity> {
        if (value.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<ArtistEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}


