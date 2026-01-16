package com.example.musium.domain.usecase

import com.example.musium.domain.model.Album
import com.example.musium.domain.repo.SongsRepository
import javax.inject.Inject

class FetchAlbumsUseCase @Inject constructor(
    private val repository: SongsRepository
) {
    suspend operator fun invoke(): List<Album> {
        return repository.fetchAlbums();
    }
}