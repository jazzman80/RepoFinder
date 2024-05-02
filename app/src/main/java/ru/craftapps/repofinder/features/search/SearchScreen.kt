package ru.craftapps.repofinder.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.features.search.SearchContract.Event
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

    // События
    val setEvent: (Event) -> Unit = { viewModel.setEvent(event = it) }

    Screen(loadState = state.loadState) {
        Column {

            TopBar(
                title = stringResource(id = R.string.search_repos),
                onDownloadButton = {
                    navigateToDownload()
                }
            )

            BasicTextField(
                modifier = Modifier
                    .semantics {
                        testTag = "Строка поиска"
                    }
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.medium
                    )
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(all = 8.dp),
                value = state.searchText,
                onValueChange = {
                    setEvent(Event.EditSearchText(it))
                },
                textStyle = MaterialTheme.typography.bodyMedium
            ) {
                if (state.searchText.isEmpty()) {
                    Text(
                        modifier = Modifier.semantics {
                            testTag = "Плейсхолдер строки поиска"
                        },
                        text = stringResource(id = R.string.start_search),
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                it()
            }
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