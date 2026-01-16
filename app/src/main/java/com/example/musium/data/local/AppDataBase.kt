package com.example.musium.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.musium.data.local.dao.UserDao
import com.example.musium.data.local.entity.UserEntity
import com.example.musium.data.local.converters.UserTypeConverters

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}