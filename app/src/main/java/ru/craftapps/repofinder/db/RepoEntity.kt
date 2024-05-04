package ru.craftapps.repofinder.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.craftapps.repofinder.features.search.RepoListItemState

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "htmlUrl") val htmlUrl: String
) {
    companion object {
        fun fromRepoListItemState(repoListItemState: RepoListItemState) = RepoEntity(
            id = repoListItemState.id,
            title = repoListItemState.title,
            author = repoListItemState.author,
            htmlUrl = repoListItemState.htmlUrl
        )
    }
}
