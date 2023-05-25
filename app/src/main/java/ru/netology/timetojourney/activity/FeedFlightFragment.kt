package ru.netology.timetojourney.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.timetojourney.R
import ru.netology.timetojourney.adapter.FlightAdapter
import ru.netology.timetojourney.adapter.OnInteractionListener
import ru.netology.timetojourney.databinding.FragmentFeedFlightBinding
import ru.netology.timetojourney.dto.Flight
import ru.netology.timetojourney.viewmodel.FlightViewModel

class FeedFlightFragment : Fragment() {

    private var _binding: FragmentFeedFlightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFeedFlightBinding.inflate(inflater, container, false)
        val viewModel: FlightViewModel by viewModels(ownerProducer = ::requireParentFragment)
        val adapter = FlightAdapter(object : OnInteractionListener {
            override fun onLike(flight: Flight) {
                viewModel.like(flight)
            }

            override fun onViewFlight(flight: Flight) {
                findNavController().navigate(
                    R.id.action_feedFlightFragment_to_flightDetailsFragment,
                    Bundle().apply {
                        putCharSequence("token", flight.searchToken)
                    })
                }
        })

        binding.listFlight.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.flights)

            binding.progress.isVisible = state.loading
            binding.errorGroup.isVisible = state.error
            binding.emptyText.isVisible = state.empty
        }

        binding.retryButton.setOnClickListener {
            viewModel.loadFlight()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
