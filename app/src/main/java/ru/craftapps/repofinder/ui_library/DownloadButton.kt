package ru.craftapps.repofinder.ui_library

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.craftapps.repofinder.R

@Composable
fun DownloadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(
                CircleShape
            )
            .clickable {
                onClick()
            }
            .background(
                color = MaterialTheme.colorScheme.secondary
            )
            .paint(
                painter = painterResource(id = R.drawable.ic_download),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
            )
            .size(30.dp)
    )
}

@Preview
@Composable
fun DownloadButtonPreview() {
    DownloadButton()
}