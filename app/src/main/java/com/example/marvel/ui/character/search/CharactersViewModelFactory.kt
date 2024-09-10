package com.example.marvel.ui.character.search

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.marvel.infra.paging.CharactersPagingSource

class CharactersViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val charactersPagingSource: CharactersPagingSource
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharactersViewModel(charactersPagingSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}