package ru.craftapps.repofinder.features.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import ru.craftapps.repofinder.ui_library.LoadState
import ru.craftapps.repofinder.ui_library.Screen

@Composable
fun SplashScreen() {
    Screen(
        loadState = LoadState.SUCCESS
    ) {

        LottieAnimation(
            composition =,
            progress =
        )

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

