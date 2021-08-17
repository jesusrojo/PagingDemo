package com.jesusrojo.pagingdemo.paging.data.model

import com.google.gson.annotations.SerializedName

data class ReposResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<RepoRaw> = emptyList(),
    val nextPage: Int? = null
)