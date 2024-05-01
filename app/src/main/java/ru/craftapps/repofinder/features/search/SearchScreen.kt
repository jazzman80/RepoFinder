package ru.craftapps.repofinder.features.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.theme.AppTheme
import ru.craftapps.repofinder.ui_library.Screen
import ru.craftapps.repofinder.ui_library.TopBar

@Composable
fun SearchScreen(
    navigateToDownload: () -> Unit = {}
) {
    // Подключение вьюмодели
    val viewModel = koinViewModel<SearchViewModel>()

    // Состояние экрана
    val state = viewModel.viewState.value

    Screen(loadState = state.loadState) {
        Column {

            TopBar(
                title = stringResource(id = R.string.search_repos),
                onDownloadButton = {
                    navigateToDownload()
                }
            )
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    KoinApplication(application = {
        androidContext(RepoFinderApp())
        modules(appModule)
    }) {
        AppTheme {
            SearchScreen()
        }
    }
}