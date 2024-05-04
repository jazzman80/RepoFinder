package ru.craftapps.repofinder.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.features.search.SearchContract.Event
import ru.craftapps.repofinder.features.search.SearchContract.Event.EditSearchText
import ru.craftapps.repofinder.features.search.SearchContract.Event.Search
import ru.craftapps.repofinder.features.search.SearchContract.State
import ru.craftapps.repofinder.theme.AppTheme
import ru.craftapps.repofinder.ui_library.Button
import ru.craftapps.repofinder.ui_library.Screen
import ru.craftapps.repofinder.ui_library.SearchBar
import ru.craftapps.repofinder.ui_library.TopBar

@Composable
fun SearchScreen(
    state: State = State(),
    setEvent: (Event) -> Unit = {},
    navigateToDownload: () -> Unit = {}
) {

    // Постраничная загрузка
    LaunchedEffect(state.listState, state) {
        snapshotFlow { state.listState.firstVisibleItemIndex }
            .collectLatest {
                if (state.loadedItems > 0 && it == state.loadedItems - 20) {
                    setEvent(
                        Event.LoadNextPage
                    )
                }
            }
    }

    Screen(loadState = state.loadState) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

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

                Button(
                    modifier = Modifier
                        .semantics {
                            testTag = "Кнопка поиска"
                        },
                    onClick = {
                        setEvent(Search)
                    }
                )
            }

            if (state.searchResultList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .semantics {
                            testTag = "Список найденных репозиториев"
                        }
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = state.listState
                ) {

                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    items(state.searchResultList.size) { index ->
                        SearchListItem(
                            state = state.searchResultList[index],
                            downloadRepo = {
                                setEvent(Event.DownloadRepo(it))
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .semantics {
                                testTag = "Сообщение ничего не найдено"
                            },
                        text = stringResource(R.string.nothing_was_found),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {

    AppTheme {
        SearchScreen(
            State(
                searchResultList = listOf(
                    RepoListItemState(
                        title = "Repo One",
                        author = "System"
                    ),
                    RepoListItemState(
                        title = "Microsoft Word",
                        author = "BGates"
                    ),
                    RepoListItemState(
                        title = "Twitter",
                        author = "Imask"
                    ),
                    RepoListItemState(
                        title = "Vkontakte",
                        author = "Durov"
                    )
                )
            )
        )
    }

}