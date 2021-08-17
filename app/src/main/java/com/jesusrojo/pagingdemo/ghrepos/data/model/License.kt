package com.jesusrojo.pagingdemo.ghrepos.data.model


import com.google.gson.annotations.SerializedName

data class License(
    @field:SerializedName("name") val licenseName: String
)