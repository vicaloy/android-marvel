package com.example.marvel.ui.character.detail

import com.example.marvel.domain.model.Characters

data class CharacterDetailUiState(
    val character: Characters = Characters(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)