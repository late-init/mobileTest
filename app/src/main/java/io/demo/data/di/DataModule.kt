package io.demo.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.demo.data.repository.BookingRepository
import io.demo.data.repository.OfflineBookingRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindsBookingRepository(impl: OfflineBookingRepository): BookingRepository
}
