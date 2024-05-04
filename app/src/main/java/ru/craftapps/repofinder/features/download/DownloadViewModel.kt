package ru.craftapps.repofinder.features.download

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.craftapps.repofinder.core.ResponseResult
import ru.craftapps.repofinder.core.mvi.BaseViewModel
import ru.craftapps.repofinder.features.download.DownloadContract.Effect
import ru.craftapps.repofinder.features.download.DownloadContract.Event
import ru.craftapps.repofinder.features.download.DownloadContract.State
import ru.craftapps.repofinder.ui_library.LoadState
import ru.craftapps.repofinder.use_case.DowloadedListUseCase

class DownloadViewModel(
    private val dowloadedList: DowloadedListUseCase
) : BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {}

    init {
        viewModelScope.launch(Dispatchers.IO) {

            val result = dowloadedList()

            if (result.first == ResponseResult.SUCCESS) {
                setState {
                    copy(
                        downloadedReposList = result.second!!,
                        loadState = LoadState.SUCCESS
                    )
                }
            } else {
                setState {
                    copy(
                        loadState = LoadState.SUCCESS
                    )
                }
            }
        }
    }

}