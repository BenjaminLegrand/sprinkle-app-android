package fr.legrand.sprinkle.presentation.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import fr.legrand.sprinkle.presentation.ui.main.navigator.MainActivityNavigatorListener
import fr.legrand.sprinkle.presentation.ui.main.navigator.MainNavigator
import fr.legrand.sprinkle.presentation.ui.plant.list.PlantListFragmentNavigatorListener
import fr.legrand.sprinkle.presentation.ui.splash.navigator.SplashscreenActivityNavigatorListener
import fr.legrand.sprinkle.presentation.ui.splash.navigator.SplashscreenNavigator

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {

    @Provides
    @ActivityScoped
    fun splashscreenActivityNavigatorListener(navigator: SplashscreenNavigator):
        SplashscreenActivityNavigatorListener = navigator

    @Provides
    @ActivityScoped
    fun plantListFragmentNavigatorListener(navigator: MainNavigator):
        PlantListFragmentNavigatorListener = navigator

    @Provides
    @ActivityScoped
    fun mainActivityNavigatorListener(navigator: MainNavigator):
        MainActivityNavigatorListener = navigator
}
