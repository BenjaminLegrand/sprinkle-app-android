package fr.legrand.sprinkle.presentation.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import fr.legrand.sprinkle.presentation.ui.splash.navigator.SplashscreenActivityNavigatorListener
import fr.legrand.sprinkle.presentation.ui.splash.navigator.SplashscreenNavigator

@Module
@InstallIn(ActivityComponent::class)
object SplashscreenActivityModule {

    @Provides
    @ActivityScoped
    fun splashscreenActivityNavigatorListener(navigator: SplashscreenNavigator):
        SplashscreenActivityNavigatorListener = navigator
}
