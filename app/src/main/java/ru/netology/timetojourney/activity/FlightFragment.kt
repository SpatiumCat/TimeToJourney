package ru.netology.timetojourney.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.timetojourney.adapter.FlightViewHolder
import ru.netology.timetojourney.adapter.OnInteractionListener
import ru.netology.timetojourney.databinding.FragmentFlightBinding
import ru.netology.timetojourney.dto.Flight
import ru.netology.timetojourney.viewmodel.FlightViewModel

class FlightFragment : Fragment() {

    private var _binding: FragmentFlightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFlightBinding.inflate(inflater, container, false)
        val viewModel: FlightViewModel by viewModels(::requireParentFragment)
        val viewHolder = FlightViewHolder(binding.flight, object : OnInteractionListener {
            override fun onLike(flight: Flight) {
            }

            override fun onViewFlight(flight: Flight) {
            }
        })

        val currentFlight = arguments?.getCharSequence("token")?.let {
            viewModel.data.value?.flights?.find { flight -> flight.searchToken == it }
        }
        currentFlight?.let { viewHolder.bind(it) }

        viewModel.data.observe(viewLifecycleOwner) {
            val updatedFlight = it.flights
                .find { flight -> flight.searchToken == currentFlight?.searchToken  }
                ?.let { updatedFlight -> viewHolder.bind(updatedFlight) }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
