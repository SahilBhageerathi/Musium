package com.example.musium.data.local.converters

import androidx.room.TypeConverter
import com.example.musium.data.local.entity.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserTypeConverters {


        private val gson = Gson()

        @TypeConverter
        fun explicitContentToJson(value: ExplicitContentEntity?): String? {
            return value?.let { gson.toJson(it) }
        }

        @TypeConverter
        fun explicitContentFromJson(value: String?): ExplicitContentEntity? {
            return value?.let { gson.fromJson(it, ExplicitContentEntity::class.java) }
        }


        @TypeConverter
        fun externalUrlsToJson(value: ExternalUrlsEntity?): String? {
            return value?.let { gson.toJson(it) }
        }

        @TypeConverter
        fun externalUrlsFromJson(value: String?): ExternalUrlsEntity? {
            return value?.let { gson.fromJson(it, ExternalUrlsEntity::class.java) }
        }

        @TypeConverter
        fun followersToJson(value: FollowersEntity?): String? {
            return value?.let { gson.toJson(it) }
        }

        @TypeConverter
        fun followersFromJson(value: String?): FollowersEntity? {
            return value?.let { gson.fromJson(it, FollowersEntity::class.java) }
        }

        @TypeConverter
        fun imagesToJson(value: List<UserImageEntity>?): String? {
            return value?.let { gson.toJson(it) }
        }

        @TypeConverter
        fun imagesFromJson(value: String?): List<UserImageEntity>? {
            if (value.isNullOrBlank()) return null

            val type = object : TypeToken<List<UserImageEntity>>() {}.type
            return gson.fromJson(value, type)
        }


}