package ru.netology.timetojourney.repository

import ru.netology.timetojourney.dto.Flight
import ru.netology.timetojourney.dto.Flights

interface FlightRepository {

    fun getFlights(callback: GetAllCallback<Flights>)

    interface GetAllCallback<T> {
        fun onSuccess(flights: T)
        fun onError(e: Exception)
    }
}


