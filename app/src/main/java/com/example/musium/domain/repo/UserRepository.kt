package com.example.musium.domain.repo

import com.example.musium.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun observeUser(): Flow<User?>
    suspend fun getUser(): User
}