package com.example.musium.di

import com.example.musium.data.remote.auth.AppConfig
import com.example.musium.data.remote.auth.AppConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigModule {

    @Binds
    abstract fun bindAppConfig(
        impl: AppConfigImpl
    ): AppConfig
}