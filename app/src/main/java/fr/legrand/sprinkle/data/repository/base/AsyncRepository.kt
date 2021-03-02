package fr.legrand.sprinkle.data.repository.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class AsyncRepository : CoroutineScope by CoroutineScope(Dispatchers.IO + SupervisorJob())