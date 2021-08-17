package com.jesusrojo.pagingdemo.paging.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jesusrojo.pagingdemo.paging.data.model.RepoEntyKey


@Dao
interface RepoEntyKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManyKeysDB(keys: List<RepoEntyKey>)

    @Query("SELECT * FROM repo_enty_key_table WHERE keyId =:keyId")
    suspend fun getEntyKeyById(keyId: Int): RepoEntyKey

    @Query("DELETE FROM repo_enty_key_table")
    suspend fun clearAllKeys()
}