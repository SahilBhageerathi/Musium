package com.example.musium.domain.usecase

import androidx.paging.PagingData
import com.example.musium.domain.model.Album
import com.example.musium.domain.repo.SongsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAlbumsUseCase @Inject constructor(
    private val repository: SongsRepository
) {
     operator fun invoke(): Flow<PagingData<Album>> {
        return repository.getNewReleasedAlbums()
    }
}