package io.demo.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkBooking(
    val shipReference: String,
    val shipToken: String,
    val canIssueTicketChecking: Boolean,
    val expiryTime: String,
    val duration: Int,
    val segments: List<Segment>
)

@Serializable
data class Segment(
    val id: String,
    @SerialName(value = "originAndDestinationPair")
    val pair: OriginAndDestinationPair
)

@Serializable
data class OriginAndDestinationPair(
    val destination: Location,
    val destinationCity: String,
    val origin: Location,
    val originCity: String
)

@Serializable
data class Location(
    val code: String,
    val displayName: String,
    val url: String
)