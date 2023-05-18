package ru.netology.timetojourney.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.netology.timetojourney.PostBody
import ru.netology.timetojourney.api.FlightApi
import ru.netology.timetojourney.dto.Flight

class FlightRepositoryImpl: FlightRepository {
    override fun getFlights(callback: FlightRepository.GetAllCallback<List<Flight>>) {
        FlightApi.retrofitService.getFlight(PostBody("LED"))
            .enqueue(object : Callback<List<Flight>> {
                override fun onResponse(
                    call: Call<List<Flight>>,
                    response: Response<List<Flight>>
                ) {
                    if (!response.isSuccessful) {
                        callback.onError(RuntimeException(response.message()))
                        return
                    }
                    callback.onSuccess(response.body() ?: throw RuntimeException("body is null"))
                }

                override fun onFailure(call: Call<List<Flight>>, t: Throwable) {
                    callback.onError(RuntimeException(t))
                }
            }
            )
    }
}
