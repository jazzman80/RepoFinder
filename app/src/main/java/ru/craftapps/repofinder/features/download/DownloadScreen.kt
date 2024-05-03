package ru.craftapps.repofinder.features.download

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.features.search.SearchListItem
import ru.craftapps.repofinder.theme.AppTheme
import ru.craftapps.repofinder.ui_library.Screen
import ru.craftapps.repofinder.ui_library.TopBar

@Composable
fun DownloadScreen(
    state: DownloadContract.State = DownloadContract.State(),
    navigateBack: () -> Unit = {}
) {
    Screen(loadState = state.loadState) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            TopBar(
                modifier = Modifier
                    .semantics {
                        testTag = "Шапка"
                    },
                title = stringResource(id = R.string.downloaded_repos),
                onBackButton = {
                    navigateBack()
                }
            )

            if (state.downloadedReposList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .semantics {
                            testTag = "Список сохранённых репозиториев"
                        }
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    items(state.downloadedReposList.size) {
                        SearchListItem(
                            state = state.downloadedReposList[it]
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
                                testTag = "Сообщение вы не сохранили ни одного репозитория"
                            },
                        text = stringResource(R.string.nothing_was_saved),
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
fun DownloadScreenPreview() {
    KoinApplication(application = {
        androidContext(RepoFinderApp())
        modules(appModule)
    }) {
        AppTheme {
            DownloadScreen()
        }
    }
}