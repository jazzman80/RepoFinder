package ru.craftapps.repofinder.use_case

import ru.craftapps.repofinder.business.Repository
import ru.craftapps.repofinder.features.search.RepoListItemState

class DownloadRepoUseCase(
    private val repository: Repository
) {
    operator fun invoke(repo: RepoListItemState) {
        val result = repository.downloadRepo(repo)
    }
}