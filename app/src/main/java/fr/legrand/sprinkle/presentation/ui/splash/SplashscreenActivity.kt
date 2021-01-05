package fr.legrand.sprinkle.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.databinding.ActivitySplashscreenBinding
import fr.legrand.sprinkle.presentation.ui.splash.navigator.SplashscreenActivityNavigatorListener
import fr.legrand.viewbinding.extensions.BindingActivity
import javax.inject.Inject

@AndroidEntryPoint
class SplashscreenActivity : BindingActivity<ActivitySplashscreenBinding>() {

    @Inject
    lateinit var navigator: SplashscreenActivityNavigatorListener

    override fun getBinding(layoutInflater: LayoutInflater): ActivitySplashscreenBinding =
        ActivitySplashscreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO load referential data before navigating
        navigator.displayMainActivity(this)
    }
}
