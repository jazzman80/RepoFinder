package ru.craftapps.repofinder.ui_library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.theme.AppTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String = ""
) {
    Text(
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
        text = title,
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleMedium
    )
}

@Preview
@Composable
fun TopBarPreview() {
    AppTheme {
        TopBar(
            title = stringResource(id = R.string.search_repos)
        )
    }
}