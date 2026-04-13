package io.demo.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.demo.log

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainScreenViewModel = viewModel()) {
  LifecycleEventEffect(Lifecycle.Event.ON_START) {
    // TODO: we should care about the configuration change, just for test
    log("MainScreen show")
    viewModel.refresh()
  }

  val state = viewModel.uiState.collectAsStateWithLifecycle()
  val uiState = state.value
  when (uiState) {
    is MainUiState.Loading -> {
      Text("Loading")
    }

    is MainUiState.Error -> {
      Text("We have a problem")
    }

    is MainUiState.Success -> {
      LazyColumn(modifier = modifier) {
        items(uiState.trips) { trip ->
          Row(modifier = Modifier.padding(18.dp)) {
            Text(text = "From:${trip.originDisplayName}")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "To: ${trip.destinationDisplayName}")
          }
        }
      }
    }
  }
}
