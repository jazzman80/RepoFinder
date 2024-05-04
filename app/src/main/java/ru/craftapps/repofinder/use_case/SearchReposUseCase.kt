package ru.craftapps.repofinder.use_case

import ru.craftapps.repofinder.business.Repository
import ru.craftapps.repofinder.core.ResponseResult
import ru.craftapps.repofinder.features.search.RepoListItemState

class SearchReposUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(
        query: String,
        page: Int
    ): Pair<ResponseResult, List<RepoListItemState>?> {
        val result = repository.searchRepos(query, page.toString())

        return if (result != null) {
            ResponseResult.SUCCESS to result.map { RepoListItemState.fromRepoDto(it) }
        } else {
            ResponseResult.ERROR to null
        }
    }
}