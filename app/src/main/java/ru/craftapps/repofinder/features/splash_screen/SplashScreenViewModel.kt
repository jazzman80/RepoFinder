package ru.craftapps.repofinder.features.splash_screen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.craftapps.repofinder.core.mvi.BaseViewModel
import ru.craftapps.repofinder.features.splash_screen.SplashScreenContract.Effect
import ru.craftapps.repofinder.features.splash_screen.SplashScreenContract.Event
import ru.craftapps.repofinder.features.splash_screen.SplashScreenContract.State

class SplashScreenViewModel() :
    BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {}

    init {
        viewModelScope.launch {
            delay(1000L)

            setState {
                copy(
                    displayTitle = true
                )
            }
        }

        viewModelScope.launch {
            delay(1300L)

            setState {
                copy(
                    displayTagline = true
                )
            }
        }

        viewModelScope.launch {
            delay(3000L)

            setEffect { Effect.NavigateToMainScreen }
        }
    }
}