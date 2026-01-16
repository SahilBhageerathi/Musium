package com.example.musium.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musium.data.local.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAlbums(): Flow<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumEntity>)

    @Query("DELETE FROM albums")
    suspend fun clearAlbums()

    @Query("SELECT COUNT(*) FROM albums")
    suspend fun getCount():Int
}