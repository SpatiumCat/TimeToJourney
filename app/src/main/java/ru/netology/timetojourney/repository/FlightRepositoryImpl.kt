package ru.netology.timetojourney.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.netology.timetojourney.PostBody
import ru.netology.timetojourney.api.FlightApi
import ru.netology.timetojourney.dto.Flight
import ru.netology.timetojourney.dto.Flights

class FlightRepositoryImpl: FlightRepository {
    override fun getFlights(callback: FlightRepository.GetAllCallback<Flights>) {
        FlightApi.retrofitService.getFlight(PostBody("LED"))
            .enqueue(object : Callback<Flights> {
                override fun onResponse(
                    call: Call<Flights>,
                    response: Response<Flights>
                ) {
                    if (!response.isSuccessful) {
                        callback.onError(RuntimeException(response.message()))
                        return
                    }
                    callback.onSuccess(response.body() ?: throw RuntimeException("body is null"))
                }

                override fun onFailure(call: Call<Flights>, t: Throwable) {
                    callback.onError(RuntimeException(t))
                }
            }
            )
    }
}
