package io.demo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.demo.data.local.entities.BookingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(ticket: BookingEntity)

  @Update
  suspend fun update(ticket: BookingEntity)

  @Delete
  suspend fun delete(ticket: BookingEntity)

  @Query("SELECT * from booking")
  fun getBookings(): Flow<List<BookingEntity>>

}