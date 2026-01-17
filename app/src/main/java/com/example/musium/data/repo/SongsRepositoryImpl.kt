package com.example.musium.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.musium.data.PagingConstants.NEW_RELEASES_LIMIT
import com.example.musium.data.mappers.toDomain
import com.example.musium.data.paging.NewReleasesPagingSource
import com.example.musium.data.remote.ApiResponse
import com.example.musium.data.remote.dataSource.AlbumsRemoteDataSource
import com.example.musium.domain.model.Album
import com.example.musium.domain.model.Playlist
import com.example.musium.domain.model.PlaylistTrackItem
import com.example.musium.domain.repo.SongsRepository
import kotlinx.coroutines.flow.Flow

class SongsRepositoryImpl(
    private val remote: AlbumsRemoteDataSource
) : SongsRepository {
    override  fun getNewReleasedAlbums(): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                pageSize = NEW_RELEASES_LIMIT,
                initialLoadSize = 2*NEW_RELEASES_LIMIT,
                prefetchDistance = NEW_RELEASES_LIMIT,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { NewReleasesPagingSource(remote) }
        ).flow
    }


    override suspend fun fetchTop50TeluguHindiPlaylist(query: String): List<Playlist> {
        when(val response = remote.searchPlaylists(query)){
            is ApiResponse.Success -> {
                return response.data.playlists.items.mapNotNull { it?.toDomain() }
            }

            is ApiResponse.Error -> {
                throw Exception(response.message)
            }
        }
    }

    override suspend fun loadMore(page: Int): List<Album> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlaylistTracks(playlistId: String): List<PlaylistTrackItem> {
        when(val response = remote.getPlaylistTracks(playlistId)){
            is ApiResponse.Success -> {
                return response.data.items.map{ it.toDomain()}
            }

            is ApiResponse.Error -> {
                throw Exception(response.message)
            }
        }
    }


}