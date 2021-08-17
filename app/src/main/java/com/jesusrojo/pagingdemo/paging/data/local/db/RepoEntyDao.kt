package com.jesusrojo.pagingdemo.paging.data.local.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jesusrojo.pagingdemo.paging.data.model.RepoEnty

@Dao
interface RepoEntyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDB(enties: List<RepoEnty>)

    @Query("SELECT * FROM repo_enty_table ORDER BY id ASC")
    fun fetchAllDB(): PagingSource<Int, RepoEnty>

    @Query("DELETE FROM repo_enty_table")
    suspend fun deleteAllDB()
}