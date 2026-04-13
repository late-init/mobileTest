package io.demo.ui.model

import io.demo.data.model.Booking

data class BookingInfo(
    val trips: List<Trip>
)

data class Trip(
    val originCity: String,
    val originDisplayName: String,
    val destinationCity: String,
    val destinationDisplayName: String
)

fun List<Booking>.mapToBookingInfo(): List<BookingInfo> = map { booking ->
    BookingInfo(
        trips = booking.segments.map { segment ->
            with(segment.pair) {
                Trip(
                    originCity = originCity,
                    originDisplayName = origin.displayName,
                    destinationCity = destinationCity,
                    destinationDisplayName = destination.displayName
                )
            }
        }
    )
}
