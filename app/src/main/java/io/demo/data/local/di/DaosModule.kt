package io.demo.data.local.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.demo.data.local.BookingDatabase
import io.demo.data.local.dao.BookingDao

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesBookingDao(
        database: BookingDatabase
    ): BookingDao = database.bookingDao()
}