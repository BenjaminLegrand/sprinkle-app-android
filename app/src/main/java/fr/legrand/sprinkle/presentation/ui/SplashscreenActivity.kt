package fr.legrand.sprinkle.presentation.ui

import android.view.LayoutInflater
import fr.legrand.sprinkle.databinding.ActivitySplashscreenBinding
import fr.legrand.viewbinding.extensions.BindingActivity

class SplashscreenActivity : BindingActivity<ActivitySplashscreenBinding>() {

    override fun getBinding(layoutInflater: LayoutInflater): ActivitySplashscreenBinding =
        ActivitySplashscreenBinding.inflate(layoutInflater)

}