package ru.craftapps.repofinder.features.download

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.theme.AppTheme
import ru.craftapps.repofinder.ui_library.Screen

@Composable
fun DownloadScreen(
    state: DownloadContract.State = DownloadContract.State()
) {
    Screen(
        loadState = state.loadState
    ) {
        Text(text = "Download")
    }
}

@Preview
@Composable
fun DownloadScreenPreview() {
    KoinApplication(application = {
        androidContext(RepoFinderApp())
        modules(appModule)
    }) {
        AppTheme {
            DownloadScreen()
        }
    }
}