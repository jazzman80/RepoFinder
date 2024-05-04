package ru.craftapps.repofinder.business

import retrofit2.awaitResponse
import ru.craftapps.repofinder.data.NetworkDataSorce
import ru.craftapps.repofinder.entity.RepoDto

class RepositoryImplementation(
    private val networkDataSorce: NetworkDataSorce
) : Repository {
    override suspend fun searchRepos(query: String, page: String): List<RepoDto>? {
        return try {
            val result = networkDataSorce.searchRepos(
                query = query,
                page = page
            ).awaitResponse()

            if (result.isSuccessful) {
                result.body()!!.items
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}