package com.example.musium.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.musium.data.PagingConstants.NEW_RELEASES_LIMIT
import com.example.musium.data.PagingConstants.START_OFFSET
import com.example.musium.data.mappers.toDomain
import com.example.musium.data.remote.ApiResponse
import com.example.musium.data.remote.dataSource.AlbumsRemoteDataSource
import com.example.musium.domain.model.Album

class NewReleasesPagingSource(
    private val remote: AlbumsRemoteDataSource,
) : PagingSource<Int, Album>() {

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            val page = state.closestPageToPosition(anchorPos)
            page?.prevKey?.plus(NEW_RELEASES_LIMIT) ?: page?.nextKey?.minus(NEW_RELEASES_LIMIT)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        return try {
            val offset = params.key ?: START_OFFSET

            val limit = NEW_RELEASES_LIMIT

            when (val response = remote.getNewReleases(limit, offset)) {
                is ApiResponse.Success -> {
                    val page = response.data.albums
                    val albums = page.items.map { it.toDomain() }

                    LoadResult.Page(
                        data = albums,
                        prevKey = if (page.previous == null) null else maxOf(page.offset - page.limit, 0),
                        nextKey = if (page.next == null) null else page.offset + page.limit
                    )
                }

                is ApiResponse.Error -> LoadResult.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
