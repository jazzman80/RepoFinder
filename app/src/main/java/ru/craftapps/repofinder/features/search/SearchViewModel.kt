package ru.craftapps.repofinder.features.search

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.craftapps.repofinder.core.ResponseResult
import ru.craftapps.repofinder.core.mvi.BaseViewModel
import ru.craftapps.repofinder.features.search.SearchContract.Effect
import ru.craftapps.repofinder.features.search.SearchContract.Event
import ru.craftapps.repofinder.features.search.SearchContract.State
import ru.craftapps.repofinder.ui_library.LoadState
import ru.craftapps.repofinder.use_case.DownloadRepoUseCase
import ru.craftapps.repofinder.use_case.SearchReposUseCase

class SearchViewModel(
    private val searchRepos: SearchReposUseCase,
    private val downloadRepo: DownloadRepoUseCase
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

            Event.Search -> {
                viewModelScope.launch {

                    setState {
                        copy(
                            loadState = LoadState.LOAD
                        )
                    }

                    val result = searchRepos(
                        query = viewState.value.searchText,
                        page = 1
                    )

                    if (result.first == ResponseResult.SUCCESS) {
                        setState {
                            copy(
                                searchResultList = result.second!!,
                                loadState = LoadState.SUCCESS,
                                loadedItems = result.second!!.size,
                                loadedPages = 1
                            )
                        }
                    } else {
                        setState {
                            copy(
                                loadState = LoadState.ERROR
                            )
                        }
                    }
                }
            }

            Event.LoadNextPage -> {
                viewModelScope.launch {

                    val loadedItems = viewState.value.loadedItems

                    setState {
                        copy(
                            loadedItems = 0
                        )
                    }

                    val result = searchRepos(
                        query = viewState.value.searchText,
                        page = viewState.value.loadedPages + 1
                    )

                    if (result.first == ResponseResult.SUCCESS) {

                        val newList = viewState.value.searchResultList.toMutableList()
                        newList.addAll(result.second!!)

                        setState {
                            copy(
                                searchResultList = newList.toList(),
                                loadedItems = loadedItems + result.second!!.size,
                                loadedPages = viewState.value.loadedPages + 1
                            )
                        }
                    } else {
                        setState {
                            copy(
                                loadedItems = loadedItems
                            )
                        }
                    }
                }
            }

            is Event.DownloadRepo -> {
                downloadRepo(event.repo)
            }
        }
    }


}