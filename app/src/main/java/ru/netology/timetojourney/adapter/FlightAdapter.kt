package ru.netology.timetojourney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.timetojourney.databinding.CardFlightBinding
import ru.netology.timetojourney.dto.Flight
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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
                    departureDate.text = parsingDate("HH:mm", flight.startDate)
                    departureCity.text = flight.startCity
                    arrivalDate.text = parsingDate("HH:mm", flight.endDate)
                   // println(parsingDate("yyyy-MM-dd HH:mm", flight.startDate))
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
    private fun parsingDate(dateFormat: String, time: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val splitTime = time.split(" ")
        val date = format.parse("${splitTime[0]} ${splitTime[1]}")
        return if(date != null) SimpleDateFormat(dateFormat, Locale.US).format(date) else ""
    }
        }



class FlightDiffCallback: DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }

}
