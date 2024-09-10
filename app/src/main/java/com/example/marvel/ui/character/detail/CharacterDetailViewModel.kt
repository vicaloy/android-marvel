package com.example.marvel.ui.character.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.coroutines.safeCatching
import com.example.common.network.Hash
import com.example.marvel.domain.action.FindCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val findCharacter: FindCharacter,
    private val savedStateHandle: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun getCharacter(characterId: Long) {
        val ts = Hash.timestamp
        val apikey = Hash.PUBLIC_KEY
        val hash = Hash.getHash()

        viewModelScope.launch(dispatcher) {
            safeCatching(
                tryBlock = {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                    val character = findCharacter(ts, apikey, hash, characterId)
                    character?.let {
                        _uiState.update {
                            it.copy(character = character, isLoading = false)
                        }
                    }
                },
                catchBlock = {
                    _uiState.update {
                        it.copy(isLoading = false, hasError = true)
                    }
                }
            )
        }
    }
}