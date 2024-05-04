package ru.craftapps.repofinder.core

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.craftapps.repofinder.business.Repository
import ru.craftapps.repofinder.business.RepositoryImplementation
import ru.craftapps.repofinder.features.search.SearchViewModel
import ru.craftapps.repofinder.features.splash_screen.SplashScreenViewModel

val appModule = module {
    singleOf(::RepositoryImplementation) {
        bind<Repository>()
    }
    single {
        Retrofit.Builder()
            .baseUrl(get<String>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::SearchViewModel)
}