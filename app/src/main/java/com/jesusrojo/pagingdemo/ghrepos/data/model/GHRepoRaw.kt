package com.jesusrojo.pagingdemo.ghrepos.data.model

import com.google.gson.annotations.SerializedName

data class GHRepoRaw(
    @field:SerializedName("name") val repoName: String?,
    @field:SerializedName("created_at") val createdAt: String?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("private") val isPrivate: Boolean?,
    @field:SerializedName("forks_count") val forksCount: Int?,
    @field:SerializedName("stargazers_count") val starsCount: Int?,
    @field:SerializedName("open_issues_count") val openIssuesCount: Int?,
    @field:SerializedName("score") val score: Double?,
    @field:SerializedName("owner") val owner: Owner?,
    @field:SerializedName("license") val license: License?
)