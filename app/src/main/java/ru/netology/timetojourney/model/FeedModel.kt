package ru.netology.timetojourney.model

import ru.netology.timetojourney.dto.Flight

data class FeedModel(
    val flights: List<Flight> = emptyList(),
    val error: Boolean = false,
    val empty: Boolean = false,
    val loading: Boolean = false
)
