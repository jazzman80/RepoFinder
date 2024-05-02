package ru.craftapps.repofinder.features.search

import ru.craftapps.repofinder.core.mvi.BaseViewModel
import ru.craftapps.repofinder.features.search.SearchContract.Effect
import ru.craftapps.repofinder.features.search.SearchContract.Event
import ru.craftapps.repofinder.features.search.SearchContract.State

class SearchViewModel(

) : BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.EditSearchText -> {
                setState {
                    copy(
                        searchText = event.value
                    )
                }
            }
        }
    }


}