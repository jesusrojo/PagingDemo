package com.jesusrojo.pagingdemo.paging.data.model


import com.google.gson.annotations.SerializedName

data class Owner(
    @field:SerializedName("login") var authorName: String? = null,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    @field:SerializedName("type") val type: String
)