package ru.craftapps.repofinder.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import ru.craftapps.repofinder.features.search.SearchContract.Event.EditSearchText
import ru.craftapps.repofinder.features.search.SearchContract.Event.Search
import ru.craftapps.repofinder.theme.AppTheme
import ru.craftapps.repofinder.ui_library.Screen
import ru.craftapps.repofinder.ui_library.SearchBar
import ru.craftapps.repofinder.ui_library.SearchButton
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
                modifier = Modifier
                    .semantics {
                        testTag = "Шапка"
                    },
                title = stringResource(id = R.string.search_repos),
                onDownloadButton = {
                    navigateToDownload()
                }
            )

            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceDim
                    )
                    .padding(
                        vertical = 10.dp,
                        horizontal = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                SearchBar(
                    modifier = Modifier
                        .semantics {
                            testTag = "Строка поиска"
                        }
                        .weight(1f),
                    value = state.searchText,
                    onValueChange = {
                        setEvent(EditSearchText(it))
                    }
                )

                SearchButton(
                    modifier = Modifier
                        .semantics {
                            testTag = "Кнопка поиска"
                        },
                    onClick = {
                        setEvent(Search)
                    }
                )
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