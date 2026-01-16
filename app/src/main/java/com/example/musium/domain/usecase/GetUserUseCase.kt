package com.example.musium.domain.usecase

import com.example.musium.domain.model.User
import com.example.musium.domain.repo.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): User {
        return repository.getUser()

    }
}