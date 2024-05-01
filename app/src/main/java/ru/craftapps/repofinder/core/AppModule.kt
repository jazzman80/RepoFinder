package ru.craftapps.repofinder.core

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.craftapps.repofinder.features.splash_screen.SplashScreenViewModel

val appModule = module {
    viewModelOf(::SplashScreenViewModel)
}