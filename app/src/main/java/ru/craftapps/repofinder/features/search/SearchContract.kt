package ru.craftapps.repofinder.features.search

import androidx.compose.foundation.lazy.LazyListState
import ru.craftapps.repofinder.core.mvi.ViewEvent
import ru.craftapps.repofinder.core.mvi.ViewSideEffect
import ru.craftapps.repofinder.core.mvi.ViewState
import ru.craftapps.repofinder.ui_library.LoadState

class SearchContract {
    data class State(
        val loadState: LoadState = LoadState.SUCCESS,
        val listState: LazyListState = LazyListState(),
        val loadedItems: Int = 0,
        val loadedPages: Int = 0,
        val searchText: String = "",
        val searchResultList: List<RepoListItemState> = listOf()
    ) : ViewState

    sealed class Event : ViewEvent {
        data class EditSearchText(val value: String) : Event()
        data object Search : Event()
        data object LoadNextPage : Event()
        data class DownloadRepo(val path: String, val name: String) : Event()
    }

    sealed class Effect : ViewSideEffect
}