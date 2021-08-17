package com.jesusrojo.pagingdemo.paging.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jesusrojo.pagingdemo.paging.data.model.RepoEntyKey
import com.jesusrojo.pagingdemo.paging.data.model.RepoEnty


@Database(
    entities = [RepoEnty::class, RepoEntyKey::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDatabaseService : RoomDatabase() {

    abstract fun myEntyDao(): RepoEntyDao
    abstract fun myEntyKeyDao(): RepoEntyKeyDao

    companion object {
        @Volatile
        private var INSTANCE: RepoDatabaseService? = null

        fun getInstance(context: Context): RepoDatabaseService =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RepoDatabaseService::class.java,
                "repo_database.db"
            ).build()
    }
}