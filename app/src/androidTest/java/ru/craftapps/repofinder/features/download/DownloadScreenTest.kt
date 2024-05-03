package ru.craftapps.repofinder.features.download

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.features.search.RepoListItemState
import ru.craftapps.repofinder.theme.AppTheme

class DownloadScreenTest {

    //region Условия
    // На экране скачанных репозиториев должна быть видна шапка с надписью "Скачанные репозитории"
    // кнопка "Назад"
    // перематываемый список скачанных репозиториев
    // если список пуст - надпись "Вы не сохранили ни одного репозитория"
    //endregion

    @get:Rule
    val composeRule = createComposeRule()
    private var context: Context? = null

    //region Экран скачанных репозиториев
    private fun setDownloadScreenMock(
        state: DownloadContract.State = DownloadContract.State()
    ) {
        composeRule.setContent {
            if (context == null) context = LocalContext.current

            AppTheme {
                DownloadScreen(
                    state = state
                )
            }
        }
    }
    //endregion

    //region Шапка
    private val topBarMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Шапка"
    )

    private val topBarTextMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Текст шапки"
    )

    @Test
    fun topBarIsDisplayed() {
        setDownloadScreenMock()

        composeRule.onNode(
            topBarMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    @Test
    fun topBarTextIsDisplayed() {
        setDownloadScreenMock()

        composeRule.onNode(
            topBarTextMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            context!!.getString(R.string.downloaded_repos)
        )
    }
    //endregion

    //region Кнопка "Назад"
    private val backButtonMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Кнопка назад"
    )

    @Test
    fun backButtonIsDisplayed() {
        setDownloadScreenMock()

        composeRule.onNode(
            backButtonMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    @Test
    fun backButtonIsClickable() {
        setDownloadScreenMock()

        composeRule.onNode(
            backButtonMatcher,
            useUnmergedTree = true
        ).assertHasClickAction()
    }
    //endregion

    //region Список найденных репозиториев
    private val reposListMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Список сохранённых репозиториев"
    )

    private val reposFakeList = listOf(
        RepoListItemState(
            title = "Repo One",
            author = "System"
        ),
        RepoListItemState(
            title = "Microsoft Word",
            author = "BGates"
        ),
        RepoListItemState(
            title = "Twitter",
            author = "Imask"
        ),
        RepoListItemState(
            title = "Vkontakte",
            author = "Durov"
        )
    )

    private fun reposListIsDisplayed() {
        composeRule.onNode(
            reposListMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun reposListIsNotDisplayed() {
        composeRule.onNode(
            reposListMatcher,
            useUnmergedTree = true
        ).assertIsNotDisplayed()
    }

    private fun reposListIsScrollable() {
        composeRule.onNode(
            reposListMatcher,
            useUnmergedTree = true
        ).assert(hasScrollAction())
    }
    //endregion


    //region Сообщение "Вы не сохранили ни одного репозитория"
    private val noSavedTitleMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Сообщение вы не сохранили ни одного репозитория"
    )

    private fun noSavedTitleDisplayed() {
        composeRule.onNode(
            noSavedTitleMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun noSavedTitleNotDisplayed() {
        composeRule.onNode(
            noSavedTitleMatcher,
            useUnmergedTree = true
        ).assertIsNotDisplayed()
    }

    private fun noSavedTitleTextDisplayed() {
        composeRule.onNode(
            noSavedTitleMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            context!!.getString(R.string.nothing_was_saved)
        )
    }
    //endregion

    @Test
    fun emptyListScreen() {
        setDownloadScreenMock()

        noSavedTitleDisplayed()
        noSavedTitleTextDisplayed()
        reposListIsNotDisplayed()
    }

    @Test
    fun populatedListScreen() {
        setDownloadScreenMock(
            state = DownloadContract.State(
                downloadedReposList = reposFakeList
            )
        )

        noSavedTitleNotDisplayed()
        reposListIsDisplayed()
        reposListIsScrollable()
    }
}