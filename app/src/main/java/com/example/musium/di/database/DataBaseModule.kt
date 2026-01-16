package com.example.musium.di.database


import android.content.Context
import androidx.room.Room
import com.example.musium.data.local.AppDatabase
import com.example.musium.data.local.dao.UserDao
import com.example.musium.data.local.dataSource.UserLocalDataSource
import com.example.musium.data.local.dataSource.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()


    @Provides
    @Singleton
    fun provideUserLocalRepository(
        userDao: UserDao
    ): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao)
    }
}