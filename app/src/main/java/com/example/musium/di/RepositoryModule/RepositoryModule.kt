package com.example.musium.di.RepositoryModule


import com.example.musium.data.local.dataSource.UserLocalDataSource
import com.example.musium.data.remote.ApiService
import com.example.musium.data.remote.dataSource.AlbumsRemoteDataSource
import com.example.musium.data.remote.dataSource.AlbumsRemoteDataSourceImpl
import com.example.musium.data.remote.dataSource.UserRemoteDataSource
import com.example.musium.data.remote.dataSource.UserRemoteDataSourceImpl
import com.example.musium.data.repo.SongsRepositoryImpl
import com.example.musium.data.repo.UserRepositoryImpl
import com.example.musium.domain.repo.SongsRepository
import com.example.musium.domain.repo.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRemoteRepository(
        api: ApiService,
    ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(api)
    }


    @Provides
    @Singleton
    fun provideUserRepository(
        remote: UserRemoteDataSource,
        local: UserLocalDataSource,
    ): UserRepository {
        return UserRepositoryImpl(remote, local)
    }


    @Provides
    @Singleton
    fun provideSongsRemoteRepository(
        api: ApiService,
    ): AlbumsRemoteDataSource {
        return AlbumsRemoteDataSourceImpl(api)
    }


    @Provides
    @Singleton
    fun provideSongsRepository(
        remote: AlbumsRemoteDataSource,
    ): SongsRepository {
        return SongsRepositoryImpl(remote)
    }

}