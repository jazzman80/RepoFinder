package ru.craftapps.repofinder.features.search

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
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.features.search.SearchContract.State
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
    private fun setSearchScreenWithViewModel() {
        composeRule.setContent {
            KoinApplication(application = {
                androidContext(RepoFinderApp())
                modules(appModule)
            }) {
                if (context == null) context = LocalContext.current

                // Подключение вьюмодели
                val viewModel = koinViewModel<SearchViewModel>()

                // Состояние экрана
                val state = viewModel.viewState.value

                // События
                val setEvent: (SearchContract.Event) -> Unit = { viewModel.setEvent(event = it) }

                AppTheme {
                    SearchScreen(
                        state = state,
                        setEvent = setEvent
                    )
                }
            }
        }
    }

    private fun setSearchScreenMock(
        state: State = State()
    ) {
        composeRule.setContent {
            if (context == null) context = LocalContext.current

            AppTheme {
                SearchScreen(
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

    private fun topBarIsDisplayed() {
        composeRule.onNode(
            topBarMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun topBarTextIsDisplayed() {
        composeRule.onNode(
            topBarTextMatcher,
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

    private val searchBarPlaceholderMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Плейсхолдер строки поиска"
    )

    private fun searchBarIsDisplayed() {
        composeRule.onNode(
            searchBarMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun searchBarPlaceholderIsDisplayed() {
        composeRule.onNode(
            searchBarPlaceholderMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            context!!.getString(R.string.start_search)
        )
    }

    private fun searchBarTextEditable() {
        val testText = "Hello world"

        composeRule.onNode(
            searchBarMatcher,
            useUnmergedTree = true
        ).performTextInput(
            testText
        )

        composeRule.onNode(
            searchBarMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            testText
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

    private fun navigationButtonIsClickable() {
        composeRule.onNode(
            navigationButtonMatcher,
            useUnmergedTree = true
        ).assertHasClickAction()
    }
    //endregion

    //region Список найденных репозиториев
    private val reposListMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Список найденных репозиториев"
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


    //region Сообщение "Ничего не найдено"
    private val notFoundTitleMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Сообщение ничего не найдено"
    )

    private fun notFoundTitleDisplayed() {
        composeRule.onNode(
            notFoundTitleMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun notFoundTitleNotDisplayed() {
        composeRule.onNode(
            notFoundTitleMatcher,
            useUnmergedTree = true
        ).assertIsNotDisplayed()
    }

    private fun notFoundTitleTextDisplayed() {
        composeRule.onNode(
            notFoundTitleMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            context!!.getString(R.string.nothing_was_found)
        )
    }
    //endregion


    @Test
    fun topBarTest() {
        setSearchScreenMock()
        topBarIsDisplayed()
        topBarTextIsDisplayed()
    }

    @Test
    fun searchBarTest() {
        setSearchScreenWithViewModel()
        searchBarIsDisplayed()
        searchBarPlaceholderIsDisplayed()
        searchBarTextEditable()
    }

    @Test
    fun searchButtonTest() {
        setSearchScreenMock()
        searchButtonIsDisplayed()
    }

    @Test
    fun navigationButtonTest() {
        setSearchScreenMock()
        navigationButtonIsDisplayed()
        navigationButtonIsClickable()
    }

    @Test
    fun emptyReposListTest() {
        setSearchScreenMock()
        notFoundTitleDisplayed()
        notFoundTitleTextDisplayed()
        reposListIsNotDisplayed()
    }

    @Test
    fun populatedReposListTest() {
        setSearchScreenMock(
            state = State(
                searchResultList = reposFakeList
            )
        )

        notFoundTitleNotDisplayed()
        reposListIsDisplayed()
        reposListIsScrollable()
    }


}