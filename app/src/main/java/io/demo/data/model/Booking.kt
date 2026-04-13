package io.demo.data.model

import io.demo.data.local.entities.BookingEntity
import io.demo.data.network.model.NetworkBooking
import io.demo.data.network.model.Segment

data class Booking(
  val expiryTime: String,
  val segments: List<Segment>
)

fun NetworkBooking.asEntity() = BookingEntity(
  shipReference = shipReference,
  shipToken = shipToken,
  canIssueTicketChecking = canIssueTicketChecking,
  expiryTime = expiryTime,
  duration = duration,
  segments = segments
)

fun BookingEntity.asExternalModel() = Booking(
  expiryTime = expiryTime,
  segments = segments,
)

