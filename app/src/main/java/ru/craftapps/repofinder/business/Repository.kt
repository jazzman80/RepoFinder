package ru.craftapps.repofinder.business

import ru.craftapps.repofinder.db.RepoEntity
import ru.craftapps.repofinder.entity.RepoDto
import ru.craftapps.repofinder.features.search.RepoListItemState

interface Repository {
    suspend fun searchRepos(query: String, page: String): List<RepoDto>?
    fun downloadRepo(repo: RepoListItemState)
    suspend fun downloadedList(): List<RepoEntity>?
}