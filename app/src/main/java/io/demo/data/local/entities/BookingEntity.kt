package io.demo.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.demo.data.network.model.Segment

@Entity(
  tableName = "booking"
)
data class BookingEntity(
  @PrimaryKey
  @ColumnInfo(name = "id")
  val shipReference: String,
  @ColumnInfo(name = "ship_token")
  val shipToken: String,
  @ColumnInfo(name = "can_issue_ticket_checking")
  val canIssueTicketChecking: Boolean,
  @ColumnInfo(name = "expiry_time")
  val expiryTime: String,
  val duration: Int,
  val segments: List<Segment>,
)