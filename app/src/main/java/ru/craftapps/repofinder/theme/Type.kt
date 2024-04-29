package ru.craftapps.repofinder.theme

import androidx.compose.material3.Typography
import ru.craftapps.repofinder.theme.font.manropeFamily

private val defaultTypography = Typography()
val AppTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = manropeFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = manropeFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = manropeFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = manropeFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = manropeFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = manropeFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = manropeFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = manropeFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = manropeFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = manropeFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = manropeFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = manropeFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = manropeFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = manropeFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = manropeFamily)
)

