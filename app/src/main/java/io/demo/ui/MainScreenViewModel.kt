package io.demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.demo.data.model.Booking
import io.demo.data.repository.BookingRepository
import io.demo.log
import io.demo.ui.model.mapToBookingInfo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
  private val bookingRepository: BookingRepository
) : ViewModel() {

  val uiState: StateFlow<MainUiState> = bookingRepository.getBookings()
    .map<List<Booking>, MainUiState> { list ->
      MainUiState.Success(list.mapToBookingInfo().flatMap { it.trips })
    }
    .catch {
      emit(MainUiState.Error)
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = MainUiState.Loading
    )

  fun refresh() {
    log("perform refresh")
    viewModelScope.launch {
      bookingRepository.refresh()
    }
  }

}