package com.example.musium.di

import android.util.Log
import com.example.musium.data.remote.ApiService
import com.example.musium.data.remote.auth.AuthInterceptor
import com.example.musium.data.remote.auth.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    private const val API_BASE_URL = "https://api.spotify.com/v1/"

    @Provides
    @Singleton
    fun provideSpotifyOkHttpClient(
        authInterceptor: AuthInterceptor,
        authenticator: TokenAuthenticator
    ): OkHttpClient {


        val logging = HttpLoggingInterceptor { message ->
            Log.d("OKHTTP", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }



        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .build()
    }

    @Provides
    @Singleton
    fun provideSpotifyRetrofit(
        client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService =
        retrofit.create(ApiService::class.java)
}