package com.jesusrojo.pagingdemo.ghrepos.presentation.hilt

import android.app.Application
import androidx.room.Room
import com.jesusrojo.pagingdemo.ghrepos.data.db.GHReposDAO
import com.jesusrojo.pagingdemo.ghrepos.data.db.GHReposDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDataBaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(app: Application): GHReposDatabase {
        return Room.databaseBuilder(app,
            GHReposDatabase::class.java,
            "ghrepos_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMyDao(database: GHReposDatabase): GHReposDAO {
        return database.getGHReposDAO()
    }
}