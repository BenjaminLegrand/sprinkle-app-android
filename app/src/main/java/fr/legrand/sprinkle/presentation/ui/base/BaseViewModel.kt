package fr.legrand.sprinkle.presentation.ui.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Default + SupervisorJob()) {
    @CallSuper
    override fun onCleared() {
        cancel()
    }
}