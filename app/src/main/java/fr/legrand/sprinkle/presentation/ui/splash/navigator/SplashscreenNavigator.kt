package fr.legrand.sprinkle.presentation.ui.splash.navigator

import android.app.Activity
import android.content.Intent
import fr.legrand.sprinkle.presentation.ui.main.MainActivity
import javax.inject.Inject

class SplashscreenNavigator @Inject constructor() : SplashscreenActivityNavigatorListener {

    override fun displayMainActivity(activity: Activity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }
}