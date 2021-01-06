package fr.legrand.sprinkle.data.model

import java.util.Date
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
data class Plant constructor(
    val id: Int,
    val name: String,
    val species: String,
    val imageUrl: String,
    val location: String,
    val exposition: Exposition,
    val lastSprinkleDate: Date?,
    val nextSprinkleDate: Date?,
    val winterSprinkleInterval: Duration,
    val summerSprinkleInterval: Duration
)
