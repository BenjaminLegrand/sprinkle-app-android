package fr.legrand.sprinkle.presentation.ui.plant.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.legrand.sprinkle.data.model.Exposition
import fr.legrand.sprinkle.data.model.Plant
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
class PlantListFragmentViewModel @ViewModelInject constructor() : ViewModel() {

    private val plantListLiveData = MutableLiveData<List<PlantViewDataWrapper>>()
    fun retrievePlantList() {
        // TODO launch coroutines
        plantListLiveData.postValue((1..10).map {
            PlantViewDataWrapper(Plant(it, "Plant $it", "Species $it", "", "", Exposition.SHADOW, Date(), null, 1.toDuration(DurationUnit.DAYS), 1.toDuration(DurationUnit.DAYS)))
        })
    }

    fun deletePlants(plantIds: List<Int>) {
        // TODO implement this
    }

    fun getPlantListLiveData(): LiveData<List<PlantViewDataWrapper>> = plantListLiveData

}