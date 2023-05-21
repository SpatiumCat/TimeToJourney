package ru.netology.timetojourney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.timetojourney.databinding.CardFlightBinding
import ru.netology.timetojourney.databinding.FragmentFeedFlightBinding
import ru.netology.timetojourney.dto.Flight
import java.text.SimpleDateFormat


interface OnInteractionListener {
    fun onLike(flight: Flight)
    fun onViewFlight(flight: Flight)
}

class FlightAdapter(
    private val onInteractionListener: OnInteractionListener
): ListAdapter<Flight, FlightViewHolder>(FlightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = CardFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = getItem(position)
        holder.bind(flight)
    }
}

class FlightViewHolder (
    private val binding: CardFlightBinding,
    private val onInteractionListener: OnInteractionListener
        ): RecyclerView.ViewHolder(binding.root) {
            fun bind(flight: Flight){
                binding.apply {
                    departureDate.text = flight.startDate.split(" ")[1]
                    departureCity.text = flight.startCity
                    arrivalDate.text = flight.endDate.split(" ")[1]
                    println(arrivalDate.text)
                    arrivalCity.text = flight.endCity
                    price.text = flight.price.toString().toRuble()
                    like.isChecked = flight.likedByMe

                    like.setOnClickListener {
                        onInteractionListener.onLike(flight)
                    }
                    binding.root.setOnClickListener {
                        onInteractionListener.onViewFlight(flight)
                    }
                }
            }
    private fun String.toRuble(): String = "$this р."
        }



class FlightDiffCallback: DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }

}
