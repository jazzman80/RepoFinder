package ru.craftapps.repofinder.features.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.theme.AppTheme

@Composable
fun SearchScreen() {
    Text(text = "Search Screen")
}

@Preview
@Composable
fun SearchScreenPreview() {
    KoinApplication(application = {
        androidContext(RepoFinderApp())
        modules(appModule)
    }) {
        AppTheme {
            SearchScreen()
        }
    }
}