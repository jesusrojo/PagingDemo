package com.jesusrojo.pagingdemo.ghrepos.presentation.hilt

import com.jesusrojo.pagingdemo.ghrepos.presentation.ui.adapter.GHReposAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//@InstallIn(ActivityComponent::class) //for GHReposActivity
class AdapterModule {

   @Singleton
   @Provides
   fun provideGHReposAdapter(): GHReposAdapter = GHReposAdapter()
}