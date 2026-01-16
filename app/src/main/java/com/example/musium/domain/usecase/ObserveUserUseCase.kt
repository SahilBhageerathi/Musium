package com.example.musium.domain.usecase

import com.example.musium.domain.model.User
import com.example.musium.domain.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<User?> = repository.observeUser()
}