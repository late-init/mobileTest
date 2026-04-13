package io.demo.data.repository

import io.demo.data.local.dao.BookingDao
import io.demo.data.model.Booking
import io.demo.data.model.asEntity
import io.demo.data.model.asExternalModel
import io.demo.data.network.NetworkDataSource
import io.demo.errorMsg
import io.demo.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class OfflineBookingRepository @Inject constructor(
  private val networkDataSource: NetworkDataSource,
  private val bookingDao: BookingDao
) : BookingRepository {
  override fun getBookings(): Flow<List<Booking>> {
    return bookingDao.getBookings()
      .map { list -> list.map { it.asExternalModel() } }
      .onEach {
        log("cache data: $it")
      }
      .catch {
        emit(emptyList())
        errorMsg("Get Booking from Room failed, please check.")
      }
  }

  override suspend fun refresh() {
    try {
      // only for test, minus 1 from assets booking.json's expiryTime
      // to keep it valid
      val now = FAKE_NOW - 1
      val bookingEntity = bookingDao.getBookings().first().firstOrNull()
      val isExpired = bookingEntity?.expiryTime?.toLong()?.let {
        now >= it
      } ?: true
      log("local cache is ${if (isExpired) "invalid" else "valid"}.")
      if (isExpired) {
        val networkBooking = networkDataSource.getBooking()
        bookingDao.insert(networkBooking.asEntity())
      }
    } catch (e: Exception) {
      if (e is CancellationException) {
        throw e
      }
      errorMsg("refresh error with cause: ${e.message}")
    }
  }

  companion object {
    private const val FAKE_NOW: Long = 1722409261
  }
}