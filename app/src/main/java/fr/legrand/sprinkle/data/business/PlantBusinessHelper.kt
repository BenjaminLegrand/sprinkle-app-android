package fr.legrand.sprinkle.data.business

import fr.legrand.sprinkle.data.model.Exposition
import fr.legrand.sprinkle.data.model.Plant
import java.util.Date
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
class PlantBusinessHelper @Inject constructor() {

    fun retrievePlantList(): List<Plant> {
        return listOf(
            Plant(
                1, "Name", "Species", "", "", Exposition.LOW_SUNLIGHT,
                Date(), Date(), 1.toDuration(DurationUnit.DAYS), 1.toDuration(DurationUnit.DAYS)
            )
        )
    }
}