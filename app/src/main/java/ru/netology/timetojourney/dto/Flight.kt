package ru.netology.timetojourney.dto

data class Flight(
    val departureCity: String,
    val arrivalCity: String,
    val departureDate: Long,
    val arrivalDate: Long,
    val price: Int,
    val likedByMe: Boolean = false,
)
