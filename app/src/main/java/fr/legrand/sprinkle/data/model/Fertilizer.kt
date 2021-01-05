package fr.legrand.sprinkle.data.model

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
data class Fertilizer(
    val winterSprinkleInterval: Duration,
    val summerSprinkleInterval: Duration
)
