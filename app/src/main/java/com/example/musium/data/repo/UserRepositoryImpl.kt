package com.example.musium.data.repo


import android.util.Log
import com.example.musium.data.local.dataSource.UserLocalDataSource
import com.example.musium.data.mappers.toDomain
import com.example.musium.data.remote.dataSource.UserRemoteDataSource
import com.example.musium.data.mappers.*
import com.example.musium.data.remote.ApiResponse
import com.example.musium.domain.model.User
import com.example.musium.domain.repo.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl @Inject constructor(
    private val remote: UserRemoteDataSource,
    private val local: UserLocalDataSource
) : UserRepository {
    override fun observeUser(): Flow<User?> {
        return local.observeUser().map { entity -> entity?.toDomain() }
    }

    override suspend fun getUser(): User {

        when (val response = remote.getCurrentUserProfile()) {

            is ApiResponse.Success -> {
                val remoteUser = response.data
                local.upsertUser(remoteUser.toEntity())
                return remoteUser.toDomain()
            }

            is ApiResponse.Error -> {
                val cachedUser = local.getUser()
                if (cachedUser != null) return cachedUser.toDomain()
                throw Exception(response.message ?: "Failed to fetch user profile")
            }
        }
    }

}
