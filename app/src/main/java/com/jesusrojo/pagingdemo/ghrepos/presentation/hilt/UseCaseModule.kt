package com.jesusrojo.pagingdemo.ghrepos.presentation.hilt

import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.DeleteAllGHReposUseCase
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.FetchGHReposUseCase
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.FetchNextGHReposUseCase
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.UpdateGHReposUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideFetchGHReposUseCase(
        repository: GHReposRepository
    ): FetchGHReposUseCase {
        return FetchGHReposUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideFetchNextGHReposUseCase(
        repository: GHReposRepository
    ): FetchNextGHReposUseCase {
        return FetchNextGHReposUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateGHReposUseCase(
        repository: GHReposRepository
    ): UpdateGHReposUseCase {
        return UpdateGHReposUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteAllGHReposUseCase(
        repository: GHReposRepository
    ): DeleteAllGHReposUseCase {
        return DeleteAllGHReposUseCase(repository)
    }
}