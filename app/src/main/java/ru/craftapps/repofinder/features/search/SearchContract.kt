package ru.craftapps.repofinder.features.search

import ru.craftapps.repofinder.core.mvi.ViewEvent
import ru.craftapps.repofinder.core.mvi.ViewSideEffect
import ru.craftapps.repofinder.core.mvi.ViewState
import ru.craftapps.repofinder.ui_library.LoadState

class SearchContract {
    data class State(
        val loadState: LoadState = LoadState.SUCCESS,
        val searchText: String = ""
    ) : ViewState

    sealed class Event : ViewEvent {
        data class EditSearchText(val value: String) : Event()
    }

    sealed class Effect : ViewSideEffect
}