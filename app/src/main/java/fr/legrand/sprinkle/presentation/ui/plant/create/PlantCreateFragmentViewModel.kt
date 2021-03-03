package fr.legrand.sprinkle.presentation.ui.plant.create

import androidx.hilt.lifecycle.ViewModelInject
import fr.legrand.sprinkle.data.model.Exposition
import fr.legrand.sprinkle.data.model.TemplateIcon
import fr.legrand.sprinkle.data.repository.PlantRepository
import fr.legrand.sprinkle.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantCreateFragmentViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : BaseViewModel() {

    fun createPlant(
        name: String,
        species: String,
        location: String,
        winterSprinkleInterval: Int,
        winterFertilizeInterval: Int,
        summerSprinkleInterval: Int,
        summerFertilizeInterval: Int,
        exposition: Exposition,
        icon: TemplateIcon
    ) {
        launch {
//            val plant = Plant(
//                name = name,
//                species = species,
//                location = location,
//
//                )
        }
    }
}