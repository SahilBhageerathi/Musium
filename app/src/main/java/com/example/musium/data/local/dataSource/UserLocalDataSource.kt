package com.example.musium.data.local.dataSource


import com.example.musium.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource{
    suspend fun getUser(): UserEntity?

    fun observeUser(): Flow<UserEntity?>
    suspend fun deleteUser()
    suspend fun upsertUser(user: UserEntity)
}
