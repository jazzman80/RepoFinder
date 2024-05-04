package ru.craftapps.repofinder.features.download

import ru.craftapps.repofinder.core.mvi.ViewEvent
import ru.craftapps.repofinder.core.mvi.ViewSideEffect
import ru.craftapps.repofinder.core.mvi.ViewState
import ru.craftapps.repofinder.features.search.RepoListItemState
import ru.craftapps.repofinder.ui_library.LoadState

class DownloadContract {
    data class State(
        val loadState: LoadState = LoadState.LOAD,
        val downloadedReposList: List<RepoListItemState> = listOf()
    ) : ViewState

    sealed class Event : ViewEvent

    sealed class Effect : ViewSideEffect
}