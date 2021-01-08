package fr.legrand.sprinkle.presentation.ui.plant.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantListFragmentViewModel @ViewModelInject constructor() : ViewModel() {

    private val plantListLiveData = MutableLiveData<List<PlantViewDataWrapper>>()
    fun retrievePlantList() {
        // TODO launch coroutines
        plantListLiveData.postValue(emptyList())
    }

    fun getPlantListLiveData(): LiveData<List<PlantViewDataWrapper>> = plantListLiveData
}