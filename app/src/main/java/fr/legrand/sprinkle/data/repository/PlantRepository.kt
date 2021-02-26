package fr.legrand.sprinkle.data.repository

import dagger.Reusable
import fr.legrand.sprinkle.data.business.PlantBusinessHelper
import fr.legrand.sprinkle.data.model.Plant
import fr.legrand.sprinkle.data.repository.base.AsyncRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Reusable
class PlantRepository @Inject constructor(
    private val plantBusinessHelper: PlantBusinessHelper
) : AsyncRepository() {

    suspend fun retrievePlantList(): List<Plant> = withContext(coroutineContext) {
        plantBusinessHelper.retrievePlantList()
    }

    suspend fun deletePlants(ids: List<Int>): Unit = withContext(coroutineContext) {
        plantBusinessHelper.deletePlants(ids)
    }

    suspend fun createPlant(plant : Plant) : Unit = withContext(coroutineContext){
        plantBusinessHelper.addPlant(plant)
    }
}