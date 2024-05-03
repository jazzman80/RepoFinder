package ru.craftapps.repofinder.features.search

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.craftapps.repofinder.theme.AppTheme

class SearchListItemTest {
    // Тест элемента списка найденных репозиториев

    //region Условия
    // Элемент списка должен отображать
    // название репозитория
    // имя автора
    // кнопку "Скачать"
    //endregion

    @get:Rule
    val composeRule = createComposeRule()
    private val titleFake = "Тестовое название"
    private val authorFake = "Тестовый автор"

    //region "Элемент списка найденных репозиториев
    @Before
    fun setSearchListItemMock() {
        composeRule.setContent {
            AppTheme {
                SearchListItem(
                    state = RepoListItemState(
                        title = titleFake,
                        author = authorFake
                    )
                )
            }
        }
    }
    //endregion


    //region Название репозитория
    private val titleMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Название репозитория"
    )

    @Test
    fun titleIsDisplayed() {
        composeRule.onNode(
            titleMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    @Test
    fun titleTextIsDisplayed() {
        composeRule.onNode(
            titleMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            titleFake
        )
    }
    //endregion


    //region Имя автора
    private val authorMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Автор"
    )

    @Test
    fun authorIsDisplayed() {
        composeRule.onNode(
            authorMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    @Test
    fun authorTextIsDisplayed(): Unit {
        composeRule.onNode(
            authorMatcher,
            useUnmergedTree = true
        ).assertTextEquals(authorFake)
    }
    //endregion


    //region Кнопка скачать
    private val downloadButtonMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Кнопка скачать"
    )

    @Test
    fun downloadButtonIsDisplayed(): Unit {
        composeRule.onNode(
            downloadButtonMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }
    //endregion

}