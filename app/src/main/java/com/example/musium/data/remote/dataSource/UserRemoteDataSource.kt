package com.example.musium.data.remote.dataSource

import android.util.Log
import com.example.musium.data.remote.ApiResponse
import com.example.musium.data.remote.ApiService
import com.example.musium.data.remote.model.UserDto
import com.example.musium.data.remote.safeApiCall
import jakarta.inject.Inject

interface UserRemoteDataSource {
    suspend fun getCurrentUserProfile(): ApiResponse<UserDto>
}


class UserRemoteDataSourceImpl @Inject constructor(
    private val api: ApiService
) : UserRemoteDataSource {
    override suspend fun getCurrentUserProfile(): ApiResponse<UserDto> {

        Log.d("API CALL 3","API CALL FOR GET USER")
        return safeApiCall {
            api.getCurrentUserProfile()
        }
    }
}