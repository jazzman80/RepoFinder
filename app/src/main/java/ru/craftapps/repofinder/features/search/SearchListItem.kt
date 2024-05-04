package ru.craftapps.repofinder.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.ui_library.Button

@Composable
fun SearchListItem(
    state: RepoListItemState = RepoListItemState(),
    downloadRepo: (String, String) -> Unit = { _, _ -> }
) {

    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .clickable {
                uriHandler.openUri(state.htmlUrl)
            }
            .shadow(
                elevation = 5.dp,
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                all = 10.dp
            )


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .semantics {
                        testTag = "Название репозитория"
                    },
                text = state.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )

            Button(
                modifier = Modifier.semantics {
                    testTag = "Кнопка скачать"
                },
                icon = R.drawable.ic_download_repo,
                iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                onClick = { downloadRepo(state.url, state.title) }
            )
        }

        Text(
            modifier = Modifier
                .semantics {
                    testTag = "Автор"
                },
            text = state.author,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchListItemPreview() {
    SearchListItem(
        RepoListItemState(
            title = "Название репозитория",
            author = "Автор"
        )
    )
}