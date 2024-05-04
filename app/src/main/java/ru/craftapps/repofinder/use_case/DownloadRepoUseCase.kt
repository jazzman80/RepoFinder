package ru.craftapps.repofinder.use_case

import ru.craftapps.repofinder.business.Repository

class DownloadRepoUseCase(
    private val repository: Repository
) {
    operator fun invoke(path: String, name: String) {
        val result = repository.downloadRepo(path, name)
    }
}