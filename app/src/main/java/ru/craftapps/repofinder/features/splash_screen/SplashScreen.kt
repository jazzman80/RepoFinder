package ru.craftapps.repofinder.features.splash_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.theme.AppTheme
import ru.craftapps.repofinder.ui_library.LoadState
import ru.craftapps.repofinder.ui_library.Screen

@Composable
fun SplashScreen(
    navigateToMainScreen: () -> Unit
) {

    // Подключение вьюмодели
    val viewModel = koinViewModel<SplashScreenViewModel>()

    // Состояние экрана
    val state = viewModel.viewState.value

    // Обработка эффектов
    LaunchedEffect(true) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is SplashScreenContract.Effect.NavigateToMainScreen -> {
                    navigateToMainScreen()
                }
            }
        }.collect()
    }

    Screen(
        loadState = LoadState.SUCCESS
    ) {

        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.logo_animation)
        )

        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(200.dp))

            LottieAnimation(
                modifier = Modifier
                    .semantics {
                        testTag = "Анимированый логотип"
                    }
                    .size(260.dp),
                composition = composition,
                iterations = 1
            )

            AnimatedVisibility(
                visible = state.displayTitle
            ) {
                Text(
                    modifier = Modifier
                        .semantics {
                            testTag = "Название приложения"
                        },
                    text = stringResource(id = R.string.application_title),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            AnimatedVisibility(
                visible = state.displayTagline
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier
                        .semantics {
                            testTag = "Тэглайн"
                        }
                        .padding(
                            horizontal = 20.dp
                        ),
                    text = stringResource(id = R.string.tagline),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    locale = "RU"
)
@Composable
fun SplashScreenPreview() {
    KoinApplication(application = {
        androidContext(RepoFinderApp())
        modules(appModule)
    }) {
        AppTheme {
            SplashScreen(
                navigateToMainScreen = {}
            )
        }
    }
}

