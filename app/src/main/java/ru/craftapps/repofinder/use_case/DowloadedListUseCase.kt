package ru.craftapps.repofinder.use_case

import ru.craftapps.repofinder.business.Repository
import ru.craftapps.repofinder.core.ResponseResult
import ru.craftapps.repofinder.features.search.RepoListItemState

class DowloadedListUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): Pair<ResponseResult, List<RepoListItemState>?> {
        val result = repository.downloadedList()

        return if (result != null) {
            ResponseResult.SUCCESS to result.map { RepoListItemState.fromRepoEntity(it) }
        } else {
            ResponseResult.ERROR to null
        }
    }
}