package ru.netology.timetojourney.repository

import ru.netology.timetojourney.PostBody
import ru.netology.timetojourney.api.FlightApi
import ru.netology.timetojourney.dto.Flight

class FlightRepositoryImpl: FlightRepository {
    override fun getFlights(): List<Flight> {
        FlightApi.retrofitService.getFlight(PostBody("LED"))
            .execute()
            .let {
                 it.body() ?: throw RuntimeException("Body is null")
                }.let {
                    return it
            }
            }
    }
