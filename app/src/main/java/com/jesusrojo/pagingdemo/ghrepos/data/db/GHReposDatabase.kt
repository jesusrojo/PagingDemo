package com.jesusrojo.pagingdemo.ghrepos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo

@Database(entities = [GHRepo::class], version = 1, exportSchema = false)
abstract  class GHReposDatabase: RoomDatabase(){
    abstract fun getGHReposDAO(): GHReposDAO
}