package fr.legrand.sprinkle.presentation.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import fr.legrand.sprinkle.presentation.ui.plant.create.navigator.PlantCreateFragmentNavigatorListener
import fr.legrand.sprinkle.presentation.ui.plant.create.navigator.PlantCreateNavigator

@Module
@InstallIn(ActivityComponent::class)
object PlantCreateActivityModule {

    @Provides
    @ActivityScoped
    fun plantCreateFragmentNavigatorListener(navigator: PlantCreateNavigator): PlantCreateFragmentNavigatorListener =
        navigator

}
