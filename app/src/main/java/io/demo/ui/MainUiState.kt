package io.demo.ui

import io.demo.ui.model.Trip


sealed interface MainUiState {
  data object Loading : MainUiState
  data object Error : MainUiState
  data class Success(
    val trips: List<Trip>
  ) : MainUiState
}