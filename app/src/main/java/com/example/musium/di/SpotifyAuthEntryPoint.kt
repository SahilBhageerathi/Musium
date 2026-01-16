package com.example.musium.di

import com.example.musium.data.remote.auth.SpotifyAuthLauncher
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SpotifyAuthEntryPoint {
    fun spotifyAuthLauncher(): SpotifyAuthLauncher
}