package ru.netology.timetojourney.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.timetojourney.R
import ru.netology.timetojourney.databinding.FragmentFlightDetailsBinding
import ru.netology.timetojourney.dto.Flight
import ru.netology.timetojourney.enums.PassengerType
import ru.netology.timetojourney.parsingDate
import ru.netology.timetojourney.toRuble
import ru.netology.timetojourney.viewmodel.FlightViewModel

class FlightDetailsFragment : Fragment() {

    private var _binding: FragmentFlightDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFlightDetailsBinding.inflate(inflater, container, false)
        val viewModel: FlightViewModel by viewModels(::requireParentFragment)

        val currentFlight = arguments?.getCharSequence("token")?.let {
            viewModel.data.value?.flights?.find { flight -> flight.searchToken == it }
        }
        currentFlight?.let {
            bindFlightDetails(it)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            val updatedFlight = it.flights
                .find { flight -> flight.searchToken == currentFlight?.searchToken }
                ?.let { updatedFlight -> bindFlightDetails(updatedFlight) }
        }

        binding.likeDetail.setOnClickListener {
            currentFlight?.let {
                viewModel.like(it)
            }
        }


        return binding.root

    }


    private fun bindFlightDetails(flight: Flight) {
        with(binding) {
            startDateDetail.text = parsingDate("HH:mm  dd MMMM, EEE", flight.startDate)
            startCityDetail.text = String
                .format(
                    resources.getString(R.string.start_city),
                    flight.startCity,
                    flight.startLocationCode
                )
            endDateDetail.text = parsingDate("HH:mm  dd MMMM, EEE", flight.endDate)
            endCityDetail.text = String
                .format(
                    resources.getString(R.string.end_city),
                    flight.endCity,
                    flight.endLocationCode
                )
            serviceClassDetail.text = String
                .format(
                    resources.getString(R.string.service_class),
                    resources.getString(flight.serviceClass.type)
                )
            likeDetail.isChecked = flight.likedByMe

            for (seat in flight.seats) {
                when (seat.passengerType) {
                    PassengerType.ADT -> {
                        if (seat.count > 0) {
                            adultSeat.visibility = View.VISIBLE
                            adultCount.visibility = View.VISIBLE
                            adultCount.text = seat.count.toString()
                        }
                    }

                    PassengerType.CHD -> {
                        if (seat.count > 0) {
                            childSeat.visibility = View.VISIBLE
                            childCount.visibility = View.VISIBLE
                            childCount.text = seat.count.toString()
                        }
                    }

                    PassengerType.INF -> {
                        if (seat.count > 0) {
                            infantSeat.visibility = View.VISIBLE
                            infantCount.visibility = View.VISIBLE
                            infantCount.text = seat.count.toString()
                        }
                    }
                }
            }

            orderButton.text = String
                .format(
                    resources.getString(R.string.order_button),
                    flight.price.toString().toRuble()
                )
        }


    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
