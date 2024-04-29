package ru.craftapps.repofinder.ui_library

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.theme.shadeColor

@Composable
fun Screen(
    loadState: LoadState,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background
            )
            .clickable(
                onClick = {},
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            )
            .fillMaxSize()
    ) {

        if (loadState != LoadState.ERROR) content()
        else {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = stringResource(id = R.string.error_network),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        if (loadState == LoadState.LOAD) {
            Box(
                modifier = Modifier
                    .background(
                        color = shadeColor
                    )
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(54.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

}

@Preview(
    showBackground = true,
    locale = "EN",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ScreenPreview() {
    MaterialTheme {
        Screen(
            loadState = LoadState.SUCCESS
        ) {
            BasicText(
                text = "Hello",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}