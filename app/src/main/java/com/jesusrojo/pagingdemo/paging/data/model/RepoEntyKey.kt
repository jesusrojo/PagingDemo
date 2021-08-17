package com.jesusrojo.pagingdemo.paging.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName = "repo_enty_key_table")
data class RepoEntyKey(
    @PrimaryKey val keyId: Long,
    val previousKey: Int?,
    val nextKey: Int?
)