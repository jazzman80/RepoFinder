package ru.craftapps.repofinder.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming
import ru.craftapps.repofinder.entity.SearchReposResponseDto

interface NetworkDataSorce {
    @GET("/search/repositories")
    fun searchRepos(
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Query("q") query: String,
        @Query("page") page: String
    ): Call<SearchReposResponseDto>

    @Streaming
    @GET("{path}/zipball")
    fun downloadRepo(
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Path(
            value = "path",
            encoded = true
        ) path: String
    ): Call<ResponseBody>


}