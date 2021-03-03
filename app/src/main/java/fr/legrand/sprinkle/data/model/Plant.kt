package fr.legrand.sprinkle.data.model

import java.util.*
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
data class Plant constructor(
    val id: Int = 0,
    val name: String,
    val species: String,
    val templateIcon: TemplateIcon,
    val location: String,
    val exposition: Exposition,
    val lastSprinkleDate: Date?,
    val nextSprinkleDate: Date?,
    val winterSprinkleInterval: Duration,
    val winterFertilizeInterval: Duration,
    val summerSprinkleInterval: Duration,
    val summerFertilizeInterval: Duration
)
