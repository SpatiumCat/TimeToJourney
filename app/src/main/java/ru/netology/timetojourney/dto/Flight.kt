package ru.netology.timetojourney.dto

import ru.netology.timetojourney.enums.PassengerType
import ru.netology.timetojourney.enums.ServiceClass
import java.io.Serializable

data class Flight(
    val startCity: String,
    val endCity: String,
    val startDate: String,
    val endDate: String,
    val startLocationCode: String,
    val endLocationCode: String,
    val serviceClass: ServiceClass,
    val price: Int,
    val likedByMe: Boolean = false,
    val seats: List<Seat>,
    val searchToken: String
)

data class Seat(
    val passengerType: PassengerType,
    val count: Int
)

data class Flights(
    val flights: List<Flight>
)
