package ru.netology.timetojourney.repository

import ru.netology.timetojourney.dto.Flight

interface FlightRepository {

    fun getFlights(callback: GetAllCallback<List<Flight>>)

    interface GetAllCallback<T> {
        fun onSuccess(flights: T)
        fun onError(e: Exception)
    }
}


