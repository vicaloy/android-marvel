package com.example.series.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SeriesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SeriesUiState(showAlert = true))
    val uiState = _uiState.asStateFlow()

    fun consumeAction() {
        _uiState.update { it.copy(showAlert = false) }
    }

    fun showAlert() {
        _uiState.update { it.copy(showAlert = true) }
    }
}