package com.nab.weather.utility

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object MainEventDispatcher {

    private val dispatcher = MutableSharedFlow<MainEvent>()
    val listener = dispatcher.asSharedFlow()

    suspend fun dispatchEvent(event: MainEvent) = dispatcher.emit(event)
}

sealed class MainEvent {
    /**
     * Navigate deep link from main
     *
     */
    class MainDeepLinkNavigate(val deeplink: String) : MainEvent()
}
