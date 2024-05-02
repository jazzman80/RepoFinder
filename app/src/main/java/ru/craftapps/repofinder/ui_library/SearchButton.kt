package ru.craftapps.repofinder.ui_library

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
fun SearchButton(
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
            .paint(
                painter = painterResource(id = R.drawable.ic_search),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
            )
            .size(30.dp)
    )
}

@Composable
@Preview
fun SearchButtonPreview() {
    SearchButton()
}