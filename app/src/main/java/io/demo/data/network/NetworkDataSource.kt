package io.demo.data.network

import io.demo.data.network.model.NetworkBooking

interface NetworkDataSource {
  suspend fun getBooking(): NetworkBooking
}