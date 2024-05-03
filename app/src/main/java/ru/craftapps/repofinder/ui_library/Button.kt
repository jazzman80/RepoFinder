package ru.craftapps.repofinder.ui_library

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.craftapps.repofinder.R

@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    @DrawableRes icon: Int = R.drawable.ic_search,
    iconColor: Color = MaterialTheme.colorScheme.tertiary
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
                painter = painterResource(id = icon),
                colorFilter = ColorFilter.tint(iconColor)
            )
            .size(30.dp)
    )
}

@Composable
@Preview
fun ButtonPreview() {
    Button()
}