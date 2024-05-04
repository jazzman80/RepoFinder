package ru.craftapps.repofinder.business

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import ru.craftapps.repofinder.R
import ru.craftapps.repofinder.data.NetworkDataSorce
import ru.craftapps.repofinder.db.AppDatabase
import ru.craftapps.repofinder.db.RepoEntity
import ru.craftapps.repofinder.entity.RepoDto
import ru.craftapps.repofinder.features.search.RepoListItemState
import java.io.File
import java.io.FileOutputStream


class RepositoryImplementation(
    private val networkDataSorce: NetworkDataSorce,
    private val localDataSource: AppDatabase,
    private val context: Context
) : Repository {

    private val scope = CoroutineScope(
        Job() + Dispatchers.IO
    )

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

    override fun downloadRepo(repo: RepoListItemState) {
        scope.launch {
            try {
                val result = networkDataSorce.downloadRepo(
                    path = repo.url
                ).awaitResponse()

                if (result.isSuccessful) {

                    val dir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

                    val fileContents = result.body()!!.bytes()

                    val file = File(dir, "${repo.title}.zip")


                    val stream = FileOutputStream(file)

                    stream.use {
                        it.write(fileContents)
                    }

                    val entity = RepoEntity.fromRepoListItemState(repo)
                    localDataSource.repoDao().insert(entity)

                    Dispatchers.Main {
                        Toast.makeText(
                            context,
                            context.getString(R.string.file_saved),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

            } catch (e: Exception) {

                Log.d("MOO", e.message!!)

                Dispatchers.Main {
                    Toast.makeText(
                        context,
                        context.getString(R.string.file_not_saved),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


    }

    override suspend fun downloadedList(): List<RepoEntity>? {
        return try {
            localDataSource.repoDao().getAll()

        } catch (e: Exception) {
            null
        }
    }

}