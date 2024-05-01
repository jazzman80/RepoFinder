package ru.craftapps.repofinder.features.search

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.theme.AppTheme

class SearchScreenTest {

    //region Условия
    // На экране поиска должна быть видна шапка с надписью "Поиск репозиториев"
    // строка для ввода названия для поиска с плейсхолдером "Начните поиск"
    // кнопка "Начать поиск"
    // кнопка "Переход на экран загрузок"
    // перематываемый список найденных репозиториев
    // если список пуст - надпись "Ничего не найдено"
    //endregion

    @get:Rule
    val composeRule = createComposeRule()
    private var context: Context? = null

    //region Экран "Поиск репозиториев"
    @Before
    fun whenSearchScreenLoaded() {
        composeRule.setContent {
            KoinApplication(application = {
                androidContext(RepoFinderApp())
                modules(appModule)
            }) {
                if (context == null) context = LocalContext.current

                AppTheme {
                    SearchScreen()
                }
            }
        }
    }
    //endregion

    //region Шапка
    private val topBarMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Шапка"
    )

    private fun topBarIsDisplayed() {
        composeRule.onNode(
            topBarMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun topBarTextIsDisplayed() {
        composeRule.onNode(
            topBarMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            context!!.getString(R.string.search_repos)
        )
    }
    //endregion

    //region Строка поиска
    private val searchBarMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Строка поиска"
    )

    private fun searchBarIsDisplayed() {
        composeRule.onNode(
            searchBarMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun searchBarTextIsDisplayed() {
        composeRule.onNode(
            searchBarMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            context!!.getString(R.string.start_search)
        )
    }
    //endregion

    //region Кнопка "Начать поиск"
    private val searchButtonMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Кнопка поиска"
    )

    private fun searchButtonIsDisplayed() {
        composeRule.onNode(
            searchButtonMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }
    //endregion

    //region Кнопка "Переход на экран загрузок"
    private val navigationButtonMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Кнопка переход на экран загрузок"
    )

    private fun navigationButtonIsDisplayed() {
        composeRule.onNode(
            navigationButtonMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }
    //endregion

    //region Список найденных репозиториев
    private val reposListMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Список найденных репозиториев"
    )

    private fun reposListIsDisplayed() {
        composeRule.onNode(
            reposListMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun reposListIsScrollable() {
        composeRule.onNode(
            reposListMatcher,
            useUnmergedTree = true
        ).assert(hasScrollAction())
    }
    //endregion


    //region Сообщение "Ничего не найдено"
    //TODO
    //endregion


    @Test
    fun topBarTest() {
        topBarIsDisplayed()
        topBarTextIsDisplayed()
    }

    @Test
    fun searchBarTest() {
        searchBarIsDisplayed()
        searchBarTextIsDisplayed()
    }

    @Test
    fun searchButtonTest() {
        searchButtonIsDisplayed()
    }

    @Test
    fun navigationButtonTest() {
        navigationButtonIsDisplayed()
    }

    @Test
    fun reposListTest() {
        reposListIsDisplayed()
        reposListIsScrollable()
    }
}