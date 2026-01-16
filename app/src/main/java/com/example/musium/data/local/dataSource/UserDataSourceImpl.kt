package com.example.musium.data.local.dataSource


import com.example.musium.data.local.dao.UserDao
import com.example.musium.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {
    override suspend fun getUser(): UserEntity? {
        return userDao.getUser()
    }

    override fun observeUser(): Flow<UserEntity?> = userDao.observeUser()

    override suspend fun deleteUser() {
        userDao.clear()
    }

    override suspend fun upsertUser(user: UserEntity) {
        userDao.upsertUser(user)
    }


}
