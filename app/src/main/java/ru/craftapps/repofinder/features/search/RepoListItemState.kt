package ru.craftapps.repofinder.features.search

import ru.craftapps.repofinder.db.RepoEntity
import ru.craftapps.repofinder.entity.RepoDto

data class RepoListItemState(
    val id: Int = 0,
    val title: String = "",
    val author: String = "",
    val htmlUrl: String = "",
    val url: String = ""
) {
    companion object {
        fun fromRepoDto(repoDto: RepoDto) = RepoListItemState(
            id = repoDto.id,
            title = repoDto.name,
            author = repoDto.owner.login,
            htmlUrl = repoDto.htmlUrl,
            url = repoDto.url
        )

        fun fromRepoEntity(repoEntity: RepoEntity) = RepoListItemState(
            id = repoEntity.id,
            title = repoEntity.title,
            author = repoEntity.author,
            htmlUrl = repoEntity.htmlUrl
        )
    }
}
