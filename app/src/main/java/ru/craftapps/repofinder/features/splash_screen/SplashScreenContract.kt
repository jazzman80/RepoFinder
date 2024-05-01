package ru.craftapps.repofinder.features.splash_screen

import ru.craftapps.repofinder.core.mvi.ViewEvent
import ru.craftapps.repofinder.core.mvi.ViewSideEffect
import ru.craftapps.repofinder.core.mvi.ViewState
import ru.craftapps.repofinder.ui_library.LoadState

class SplashScreenContract {
    data class State(
        val loadState: LoadState = LoadState.SUCCESS,
        val displayTitle: Boolean = false,
        val displayTagline: Boolean = false
    ) : ViewState

    sealed class Event : ViewEvent

    sealed class Effect : ViewSideEffect {
        data object NavigateToMainScreen : Effect()
    }
}