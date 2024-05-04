package ru.craftapps.repofinder.features.search

import ru.craftapps.repofinder.entity.RepoDto

data class RepoListItemState(
    val title: String = "",
    val author: String = "",
    val htmlUrl: String = "",
    val url: String = ""
) {
    companion object {
        fun fromRepoDto(repoDto: RepoDto) = RepoListItemState(
            title = repoDto.name,
            author = repoDto.owner.login,
            htmlUrl = repoDto.htmlUrl,
            url = repoDto.url
        )
    }
}
