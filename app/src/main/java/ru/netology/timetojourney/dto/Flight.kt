package ru.netology.timetojourney.dto

data class Flight(
    val startCity: String,
    val endCity: String,
    val startDate: String,
    val endDate: String,
    val startLocationCode: String,
    val endLocationCode: String,
    val serviceClass: String,
    val price: Int,
    val likedByMe: Boolean = false,
    val seats: List<Seat>,
    val searchToken: String
)

data class Seat(
    val passengerType: String,
    val count: Int
)
