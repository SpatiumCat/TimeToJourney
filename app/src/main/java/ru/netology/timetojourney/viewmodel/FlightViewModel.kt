package ru.netology.timetojourney.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okio.IOException
import ru.netology.timetojourney.dto.Flight
import ru.netology.timetojourney.model.FeedModel
import ru.netology.timetojourney.repository.FlightRepository
import ru.netology.timetojourney.repository.FlightRepositoryImpl

private val flights: List<Flight> = listOf(
    Flight("Калининград", "Saint-Petersburg", 123512L, 495949L, 12500 ),
    Flight("Moscow", "Ekaterinburg", 123115L, 345234L, 25450),
    Flight("Smolensk", "Berlin", 3617582, 7878787, 40000)
)

class FlightViewModel(application: Application): AndroidViewModel(application) {

    private val _data = MutableLiveData<FeedModel>(FeedModel(flights))
    private val repository: FlightRepository = FlightRepositoryImpl()

    val data: LiveData<FeedModel>
        get() = _data

    fun like(flight: Flight) {
        _data.value = _data.value?.copy(flights = _data.value?.flights.orEmpty().map {
            if(flight == it) it.copy(likedByMe = !flight.likedByMe) else it
        })
    }

    init {
        loadFlight()
    }

    fun loadFlight() {
        _data.value = FeedModel(loading = true)

        _data.value =
            try{
                FeedModel( flights = repository.getFlights())
            } catch (e: IOException) {
                FeedModel(error = true)
            }
    }

}
