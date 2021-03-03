package fr.legrand.sprinkle.presentation.ui.plant.create.navigator

import android.app.Activity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PlantCreateNavigator @Inject constructor() : PlantCreateFragmentNavigatorListener {
    override fun onCancelClick(activity: Activity) {
        activity.finish()
    }
}