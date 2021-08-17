package com.jesusrojo.pagingdemo.ghrepos.data.db

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo

@Dao
interface GHReposDAO {

    @WorkerThread
    @Query("SELECT * FROM ghrepos_table")
    fun fetchAll(): List<GHRepo>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entys: List<GHRepo>)

    @WorkerThread
    @Query("DELETE FROM ghrepos_table")
    suspend fun deleteAll()
}