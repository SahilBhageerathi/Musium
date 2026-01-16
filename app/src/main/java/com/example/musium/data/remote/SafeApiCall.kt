package com.example.musium.data.remote

import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

sealed class ApiResponse<T>() {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(
        val message: String,
        val throwable: Throwable? = null,
        val data: T? = null
    ) : ApiResponse<T>()

}


suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): ApiResponse<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ApiResponse.Success(data = body)
            } else {
                ApiResponse.Error(message = "Response Body is Null")
            }

        } else {
            ApiResponse.Error(message = "Error: ${response.code()} - ${response.message()}")
        }

    } catch (e: SocketTimeoutException) {
        ApiResponse.Error(message = "Timeout Error: ${e.message}", e)
    } catch (e: java.io.IOException) {
        ApiResponse.Error(message = "IO Error: ${e.message}", e)
    } catch (e: java.net.UnknownHostException) {
        ApiResponse.Error(message = "Network Error: ${e.message}", e)
    } catch (e: HttpException) {
        ApiResponse.Error(message = "Http Error: ${e.message}", e)
    } catch (e: Exception) {
        ApiResponse.Error(message = e.message ?: "An unknown Error Occurred", e)
    }
}