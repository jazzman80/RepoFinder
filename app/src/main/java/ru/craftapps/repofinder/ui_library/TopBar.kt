package ru.craftapps.repofinder.ui_library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.theme.AppTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onDownloadButton: (() -> Unit)? = null,
    onBackButton: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 3.dp
            )
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (onBackButton != null) {
            Button(
                modifier = Modifier.semantics {
                    testTag = "Кнопка назад"
                },
                icon = R.drawable.ic_back,
                onClick = { onBackButton() },
                iconColor = MaterialTheme.colorScheme.onPrimary
            )
        }

        Text(
            modifier = Modifier
                .semantics {
                    testTag = "Текст шапки"
                }
                .weight(1f),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium
        )

        if (onDownloadButton != null) {
            DownloadButton(
                modifier = Modifier.semantics {
                    testTag = "Кнопка переход на экран загрузок"
                },
                onClick = { onDownloadButton() }
            )
        }

    }
}

@Preview
@Composable
fun TopBarPreview() {
    AppTheme {
        TopBar(
            title = stringResource(id = R.string.search_repos),
            onDownloadButton = {}
        )
    }
}