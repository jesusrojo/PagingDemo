package com.jesusrojo.pagingdemo.paging.data.model

sealed class UiModel {
    data class RepoItem(val enty: RepoEnty) : UiModel()
    data class SeparatorItem(val status: String) : UiModel()
}

val UiModel.RepoItem.roundedStarCount: Int
    get() = this.enty.starsCount / 10_000