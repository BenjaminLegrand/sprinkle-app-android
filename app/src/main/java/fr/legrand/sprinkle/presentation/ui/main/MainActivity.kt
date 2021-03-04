package fr.legrand.sprinkle.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.databinding.ActivityMainBinding
import fr.legrand.sprinkle.presentation.ui.main.navigator.MainActivityNavigatorListener
import fr.legrand.viewbinding.extensions.BindingActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>() {

    @Inject
    lateinit var navigatorListener: MainActivityNavigatorListener

    override fun getBinding(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.main_activity_container)

        navigatorListener.setNavController(navController)

        binding {
            mainActivityBottomNavView.setupWithNavController(navController)
            mainActivityBottomNavView.setOnNavigationItemReselectedListener {
                // Nothing to do
            }
        }
    }
}
