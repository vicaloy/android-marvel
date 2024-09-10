package com.example.comics.ui

import androidx.paging.PagingData
import com.example.comics.domain.model.Comics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ComicsUiState(
    val comics: Flow<PagingData<Comics>> = emptyFlow(),
    val query: String = "",
    val hasError: Boolean = false
)