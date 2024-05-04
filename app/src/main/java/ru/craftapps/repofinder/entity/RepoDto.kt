package ru.craftapps.repofinder.entity

import com.google.gson.annotations.SerializedName

data class RepoDto(
    val name: String,
    val owner: OwnerDto,
    @SerializedName("html_url")
    val htmlUrl: String,
    val url: String
)