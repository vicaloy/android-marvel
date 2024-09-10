package com.example.comics.infra.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.comics.domain.action.GetComics
import com.example.comics.domain.model.Comics
import kotlinx.coroutines.CancellationException

class ComicsPagingSource(private val getComics: GetComics) :
    PagingSource<Int, Comics>() {

    private var ts: String = ""
    private var apikey: String = ""
    private var hash: String = ""
    private var query: String? = null

    fun setAtt(
        ts: String,
        apikey: String,
        hash: String,
        query: String
    ) {
        this.ts = ts
        this.apikey = apikey
        this.hash = hash
        this.query = query.takeIf { it.isNotEmpty() }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comics> = try {
        val offset = params.key ?: STARTING_PAGE_INDEX
        val limit = params.loadSize
        val characters = getComics(
            ts,
            apikey,
            hash,
            query,
            limit,
            offset
        )

        LoadResult.Page(
            data = characters,
            prevKey = if (offset == 0) null else offset - limit,
            nextKey = if (characters.size < limit) null else offset + limit
        )

    } catch (e: CancellationException) {
        throw e
    } catch (exception: Exception) {
        LoadResult.Error(exception)
    }

    override fun getRefreshKey(state: PagingState<Int, Comics>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}