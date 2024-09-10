package com.example.comics.ui

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.comics.infra.paging.ComicsPagingSource

class ComicsViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val comicsPagingSource: ComicsPagingSource
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ComicsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ComicsViewModel(comicsPagingSource, handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}