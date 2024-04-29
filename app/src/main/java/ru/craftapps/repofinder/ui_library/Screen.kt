package ru.craftapps.repofinder.ui_library

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import ru.iflex.mobile.charity.R
import ru.iflex.mobile.charity.theme.bodyMedium
import ru.iflex.mobile.charity.theme.primaryColor
import ru.iflex.mobile.charity.theme.shadeColor
import ru.iflex.mobile.charity.theme.whiteColor

@Composable
fun Screen(
    loadState: LoadState,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = whiteColor
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

        if (loadState == LoadState.LOAD) {

            Popup {
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
                        color = primaryColor
                    )
                }
            }
        }

        if (loadState != LoadState.ERROR) content()
        else {
            BasicText(
                modifier = Modifier
                    .align(Alignment.Center),
                text = stringResource(id = R.string.error_network),
                style = bodyMedium
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    Screen(
        loadState = LoadState.ERROR
    ) {
        BasicText(text = "Hello")
    }
}