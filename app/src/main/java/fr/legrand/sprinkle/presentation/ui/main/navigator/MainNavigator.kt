package fr.legrand.sprinkle.presentation.ui.main.navigator

import androidx.navigation.NavController
import dagger.hilt.android.scopes.ActivityScoped
import fr.legrand.sprinkle.presentation.ui.plant.list.PlantListFragmentDirections
import fr.legrand.sprinkle.presentation.ui.plant.list.PlantListFragmentNavigatorListener
import javax.inject.Inject

@ActivityScoped
class MainNavigator @Inject constructor() :
    PlantListFragmentNavigatorListener, MainActivityNavigatorListener {

    private lateinit var navController: NavController

    override fun displayPlantDetails(id: Int) {
        TODO("Not yet implemented")
    }

    override fun displayPlantCreation() {
        navController.navigate(PlantListFragmentDirections.actionPlantListFragmentToPlantCreateActivity())
    }

    override fun setNavController(navController: NavController) {
        this.navController = navController
    }
}