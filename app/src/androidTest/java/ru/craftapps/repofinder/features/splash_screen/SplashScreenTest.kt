package ru.craftapps.repofinder.features.splash_screen

import android.os.SystemClock.sleep
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.core.RepoFinderApp
import ru.craftapps.repofinder.core.appModule
import ru.craftapps.repofinder.theme.AppTheme

class SplashScreenTest {

    //region Условия
    // На экране приветствия (Splash Screen) проигрывается анимация логoтипа приложения
    // После этого появляется название приложения и теглайн
    //endregion

    @get:Rule
    val composeRule = createAndroidComposeRule<SplashScreenActivity>()

    //region Экран приветствия
    private fun whenSplashScreenLoaded() {
        composeRule.setContent {
            KoinApplication(application = {
                androidContext(RepoFinderApp())
                modules(appModule)
            }) {
                AppTheme {
                    SplashScreen()
                }
            }
        }
    }
    //endregion

    //region Анимированый логотип
    private val animatedLogoMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Анимированый логотип"
    )

    private fun logoIsDisplayed() {
        composeRule.onNode(
            animatedLogoMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }
    //endregion

    //region Название приложения
    private val applicationTitleMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Название приложения"
    )

    private fun titleIsDisplayed() {
        composeRule.onNode(
            applicationTitleMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun titleIsNotDisplayed() {
        composeRule.onNode(
            applicationTitleMatcher,
            useUnmergedTree = true
        ).assertIsNotDisplayed()
    }

    private fun titleTextIsDisplayed() {
        composeRule.onNode(
            applicationTitleMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            composeRule.activity.getString(R.string.application_title)
        )
    }
    //endregion

    //region Тэглайн
    private val taglineMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.TestTag,
        "Тэглайн"
    )

    private fun taglineIsDisplayed() {
        composeRule.onNode(
            taglineMatcher,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    private fun taglineIsNotDisplayed() {
        composeRule.onNode(
            taglineMatcher,
            useUnmergedTree = true
        ).assertIsNotDisplayed()
    }

    private fun taglineTextIsDisplayed() {
        composeRule.onNode(
            taglineMatcher,
            useUnmergedTree = true
        ).assertTextEquals(
            composeRule.activity.getString(R.string.tagline)
        )
    }
    //endregion

    //region Отображение экрана приветствия на старте
    @Test
    fun startSplashScreenTest() {
        // На экране приветсвия
        whenSplashScreenLoaded()

        // Отображается логотип
        logoIsDisplayed()

        // Не отображается название приложения
        titleIsNotDisplayed()

        // Не отображается тэглайн
        taglineIsNotDisplayed()
    }
    //endregion


    //region Отображение экрана приветсвия на финише
    @Test
    fun finishSplashScreenTest() = runTest {
        // На экране приветсвия
        whenSplashScreenLoaded()

        sleep(2000L)

        // Отображается логотип
        logoIsDisplayed()

        // Отображается название приложения
        titleIsDisplayed()

        // С необходимым текстом
        titleTextIsDisplayed()

        // Отображается тэглайн
        taglineIsDisplayed()

        // С необходимым текстом
        taglineTextIsDisplayed()
    }
    //endregion
}