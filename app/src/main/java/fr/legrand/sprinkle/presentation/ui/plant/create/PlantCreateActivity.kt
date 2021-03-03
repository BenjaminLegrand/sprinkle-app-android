package fr.legrand.sprinkle.presentation.ui.plant.create

import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.databinding.ActivityPlantCreateBinding
import fr.legrand.viewbinding.extensions.BindingActivity

@AndroidEntryPoint
class PlantCreateActivity : BindingActivity<ActivityPlantCreateBinding>() {
    override fun getBinding(layoutInflater: LayoutInflater) = ActivityPlantCreateBinding.inflate(layoutInflater)
}