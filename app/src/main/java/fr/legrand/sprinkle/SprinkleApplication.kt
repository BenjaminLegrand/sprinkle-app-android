package fr.legrand.sprinkle

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SprinkleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Init timber
        Timber.plant(Timber.DebugTree())
    }
}