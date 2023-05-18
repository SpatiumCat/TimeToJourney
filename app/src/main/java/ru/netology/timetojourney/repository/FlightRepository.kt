package ru.netology.timetojourney.repository

import ru.netology.timetojourney.dto.Flight

interface FlightRepository {

    fun getFlights (): List<Flight>
}
