package ru.craftapps.repofinder.core

import android.app.Application
import org.koin.core.context.startKoin

class RepoFinderApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}