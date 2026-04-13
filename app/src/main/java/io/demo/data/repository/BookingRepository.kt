package io.demo.data.repository

import io.demo.data.model.Booking
import kotlinx.coroutines.flow.Flow

interface BookingRepository {
    fun getBookings(): Flow<List<Booking>>

    suspend fun refresh()
}