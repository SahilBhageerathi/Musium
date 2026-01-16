package com.example.musium.domain.usecase

import com.example.musium.domain.model.Playlist
import com.example.musium.domain.repo.SongsRepository
import javax.inject.Inject

class SearchTeluguHindiPlayListUseCase @Inject constructor(
    private val repository: SongsRepository
) {
    suspend operator fun invoke(): List<Playlist> =
        repository.fetchTop50TeluguHindiPlaylist("top hits telugu, hindi,")
}