package com.example.musium.domain.usecase


import com.example.musium.domain.model.PlaylistTrackItem
import com.example.musium.domain.repo.SongsRepository
import javax.inject.Inject

class FetchPlayListTrackUseCase @Inject constructor(
    private val repository: SongsRepository
) {
    suspend operator fun invoke(playlistId: String): List<PlaylistTrackItem> {
        return repository.getPlaylistTracks(playlistId)
    }
}