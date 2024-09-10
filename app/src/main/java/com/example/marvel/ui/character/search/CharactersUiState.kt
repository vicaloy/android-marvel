package com.example.marvel.ui.character.search

import androidx.paging.PagingData
import com.example.comics.domain.model.Comics
import com.example.marvel.domain.model.Characters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersUiState(
    val characters: Flow<PagingData<Characters>> = emptyFlow(),
    val query: String = "",
    val hasError: Boolean = false
)