package com.jesusrojo.pagingdemo.ghrepos.data.model

import com.google.gson.annotations.SerializedName

data class GHReposList(
    @field:SerializedName("items") val items: List<GHRepoRaw>
)