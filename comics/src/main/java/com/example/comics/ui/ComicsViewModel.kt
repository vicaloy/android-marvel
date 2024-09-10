package com.example.comics.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.comics.domain.model.Comics
import com.example.comics.infra.paging.ComicsPagingSource
import com.example.common.network.Hash
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ComicsViewModel(
    private val comicsPagingSource: ComicsPagingSource,
    private val savedStateHandle: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ComicsUiState(
            comics = getPagingData().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PagingData.empty()
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    private fun getPagingData(query: String = ""): Flow<PagingData<Comics>> {
        val ts = Hash.timestamp
        val apikey = Hash.PUBLIC_KEY
        val hash = Hash.getHash()
        comicsPagingSource.setAtt(ts, apikey, hash, query)
        return Pager(
            PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false)
        ) {
            comicsPagingSource
        }.flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val PAGE_SIZE = 15
    }
}
