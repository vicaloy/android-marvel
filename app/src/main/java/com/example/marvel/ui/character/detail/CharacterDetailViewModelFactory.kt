package com.example.marvel.ui.character.detail

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.comics.domain.action.FindComic
import com.example.marvel.domain.action.FindCharacter

class CharacterDetailViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val findCharacter: FindCharacter
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterDetailViewModel(findCharacter, handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}