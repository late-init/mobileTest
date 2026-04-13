package io.demo.data.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.demo.data.network.NetworkDataSource
import io.demo.data.network.fake.BookingRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
  @Binds
  abstract fun bindsNetworkDataSource(impl: BookingRemoteDataSource): NetworkDataSource
}