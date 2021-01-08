package fr.legrand.sprinkle.data.repository

import fr.legrand.sprinkle.data.business.PlantBusinessHelper
import fr.legrand.sprinkle.data.model.Plant
import fr.legrand.sprinkle.data.repository.base.AsyncRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantRepository @Inject constructor(
    private val plantBusinessHelper: PlantBusinessHelper
) : AsyncRepository() {

    suspend fun retrievePlantList(): Flow<List<Plant>> = withContext(coroutineContext) {
        flowOf(plantBusinessHelper.retrievePlantList())
    }
}