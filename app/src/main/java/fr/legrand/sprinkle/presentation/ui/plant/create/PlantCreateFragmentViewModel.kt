package fr.legrand.sprinkle.presentation.ui.plant.create

import androidx.hilt.lifecycle.ViewModelInject
import fr.legrand.sprinkle.data.repository.PlantRepository
import fr.legrand.sprinkle.presentation.ui.base.BaseViewModel
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantCreateFragmentViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : BaseViewModel() {
}