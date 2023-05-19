package ru.netology.timetojourney.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okio.IOException
import ru.netology.timetojourney.dto.Flight
import ru.netology.timetojourney.dto.Flights
import ru.netology.timetojourney.model.FeedModel
import ru.netology.timetojourney.repository.FlightRepository
import ru.netology.timetojourney.repository.FlightRepositoryImpl

//private val flights: List<Flight> = listOf(
//    Flight("Калининград", "Saint-Petersburg", 123512L, 495949L, 12500 ),
//    Flight("Moscow", "Ekaterinburg", 123115L, 345234L, 25450),
//    Flight("Smolensk", "Berlin", 3617582, 7878787, 40000)
//)

class FlightViewModel(application: Application): AndroidViewModel(application) {

    private val _data = MutableLiveData<FeedModel>()
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

        repository.getFlights(object : FlightRepository.GetAllCallback<Flights> {
            override fun onSuccess(flights: Flights) {
                _data.value = FeedModel(flights = flights.flights, empty = flights.flights.isEmpty())
            }

            override fun onError(e: Exception) {
                _data.value = FeedModel(error = true)
            }
        })

    }

}
