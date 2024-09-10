package com.example.marvel.ui.character.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.common.network.Hash
import com.example.marvel.domain.model.Characters
import com.example.marvel.infra.paging.CharactersPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CharactersViewModel(
    private val charactersPagingSource: CharactersPagingSource,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CharactersUiState(
            characters = getPagingData().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PagingData.empty()
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    fun fetchCharacters(query: String) {
        _uiState.update {
            it.copy(
                characters = getPagingData(query),
                query = query
            )
        }
    }

    private fun getPagingData(query: String = ""): Flow<PagingData<Characters>> {
        val ts = Hash.timestamp
        val apikey = Hash.PUBLIC_KEY
        val hash = Hash.getHash()
        charactersPagingSource.setAtt(ts, apikey, hash, query)
        return Pager(
            PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false)
        ) {
            charactersPagingSource
        }.flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val PAGE_SIZE = 15
    }
}