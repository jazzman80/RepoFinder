package ru.craftapps.repofinder.navigation

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.core.MainActivity

class NavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    //region Условия
    // Если пользователь находится на экране поиска репозиториев
    // То при нажатии кнопки "Переход на экран загрузок"
    // Он переходит на экран скачанных репозиториев (видит надпись в шапке "Сохранённые репозитории")
    // Затем при нажатии кнопки "Назад"
    // Он переходит на экран "Поиск репозиториев" (видит надпись в шапке "Поиск репозиториев")
    //endregion

    @Test
    fun navigationTest() {
        clickOnNavigationButton()
        downloadScreenTitleIsDisplayed()

        clickOnBackButton()
        searchScreenTitleIsDisplayed()
    }

    //region Кнопка "Переход на экран загрузок"
    private val navigationButtonMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Кнопка переход на экран загрузок"
    )

    private fun clickOnNavigationButton() {
        composeRule.onNode(
            navigationButtonMatcher,
            useUnmergedTree = true
        ).performClick()
    }
    //endregion

    //region Кнопка "Назад"
    private val backButtonMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Кнопка назад"
    )

    private fun clickOnBackButton() {
        composeRule.onNode(
            backButtonMatcher,
            useUnmergedTree = true
        ).performClick()
    }
    //endregion

    //region Текст шапки
    private val topBarTextMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Текст шапки"
    )

    private fun searchScreenTitleIsDisplayed() {
        composeRule.onNode(
            topBarTextMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            composeRule.activity.getString(R.string.search_repos)
        )
    }

    private fun downloadScreenTitleIsDisplayed() {
        composeRule.onNode(
            topBarTextMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            composeRule.activity.getString(R.string.downloaded_repos)
        )
    }
    //endregion

}