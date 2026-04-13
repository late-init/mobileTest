package io.demo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.demo.data.local.dao.BookingDao
import io.demo.data.local.entities.BookingEntity
import io.demo.data.local.util.SegmentConverter

@Database(
  entities = [BookingEntity::class],
  version = 1
)
@TypeConverters(
  SegmentConverter::class
)
abstract class BookingDatabase : RoomDatabase() {
  abstract fun bookingDao(): BookingDao
}