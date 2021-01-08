package fr.legrand.sprinkle.presentation.ui.plant.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.legrand.sprinkle.data.repository.PlantRepository
import fr.legrand.sprinkle.presentation.ui.base.BaseViewModel
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantListFragmentViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : BaseViewModel() {

    private val plantListLiveData = MutableLiveData<List<PlantViewDataWrapper>>()

    fun retrievePlantList() {
        launch {
            plantRepository.retrievePlantList().collect {
                plantListLiveData.postValue(it.map(::PlantViewDataWrapper))
            }
        }
    }

    fun getPlantListLiveData(): LiveData<List<PlantViewDataWrapper>> = plantListLiveData
}