package fr.legrand.sprinkle.data.business

import fr.legrand.sprinkle.data.model.Plant
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.ExperimentalTime

//TODO put reusable when storage layer is done
@ExperimentalTime
@Singleton
class PlantBusinessHelper @Inject constructor() {

    //TODO add storage layer
    private val plantList = mutableListOf<Plant>()

    fun retrievePlantList(): List<Plant> {
        return plantList
    }

    fun deletePlants(ids: List<Int>) {
        plantList.removeAll { it.id in ids }
    }

    fun addPlant(plant: Plant) {
        plantList.add(plant)
    }
}