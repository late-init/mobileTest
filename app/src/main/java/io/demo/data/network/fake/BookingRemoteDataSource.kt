package io.demo.data.network.fake

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.demo.data.di.DemoDispatchers.IO
import io.demo.data.di.Dispatcher
import io.demo.data.network.NetworkDataSource
import io.demo.data.network.model.NetworkBooking
import io.demo.log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class BookingRemoteDataSource @Inject constructor(
  @ApplicationContext private val context: Context,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
  private val json: Json,
) : NetworkDataSource {
  @OptIn(ExperimentalSerializationApi::class)
  override suspend fun getBooking(): NetworkBooking {
    val result: NetworkBooking = withContext(ioDispatcher) {
      context.assets.open(BOOKING_ASSET).use { inputStream ->
        json.decodeFromStream(inputStream)
      }
    }
    log("Network: mock get from network")
    log("network data: $result")
    return result
  }

  companion object {
    private const val BOOKING_ASSET = "booking.json"
  }
}