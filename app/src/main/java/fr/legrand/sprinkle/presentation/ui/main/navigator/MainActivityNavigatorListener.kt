package fr.legrand.sprinkle.presentation.ui.main.navigator

import androidx.navigation.NavController

interface MainActivityNavigatorListener {
    fun setNavController(navController: NavController)
}