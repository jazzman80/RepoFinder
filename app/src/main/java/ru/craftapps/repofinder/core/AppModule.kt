package ru.craftapps.repofinder.core

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.craftapps.repofinder.business.Repository
import ru.craftapps.repofinder.business.RepositoryImplementation
import ru.craftapps.repofinder.data.NetworkDataSorce
import ru.craftapps.repofinder.features.search.SearchViewModel
import ru.craftapps.repofinder.features.splash_screen.SplashScreenViewModel
import ru.craftapps.repofinder.use_case.DownloadRepoUseCase
import ru.craftapps.repofinder.use_case.SearchReposUseCase

val appModule = module {
    singleOf(::RepositoryImplementation) {
        bind<Repository>()
    }

//    val gson: Gson = GsonBuilder()
//        .
////        .registerTypeAdapter(CommentsResponse::class.java, CommentsResponseSerializer())
//        .create()


    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkDataSorce::class.java)
    }
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::SearchViewModel)

    factoryOf(::SearchReposUseCase)
    factoryOf(::DownloadRepoUseCase)
}