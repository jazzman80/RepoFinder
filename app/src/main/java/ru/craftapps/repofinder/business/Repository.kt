package ru.craftapps.repofinder.business

import ru.craftapps.repofinder.entity.RepoDto

interface Repository {
    suspend fun searchRepos(query: String, page: String): List<RepoDto>?
}