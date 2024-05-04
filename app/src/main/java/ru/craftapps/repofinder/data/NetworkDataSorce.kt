package ru.craftapps.repofinder.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.craftapps.repofinder.entity.SearchReposResponseDto

interface NetworkDataSorce {
    @GET("/search/repositories")
    fun searchRepos(
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Query("q") query: String,
        @Query("page") page: String
    ): Call<SearchReposResponseDto>
}