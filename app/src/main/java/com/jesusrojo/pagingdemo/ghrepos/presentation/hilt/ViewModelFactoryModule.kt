package com.jesusrojo.pagingdemo.ghrepos.presentation.hilt

import com.jesusrojo.pagingdemo.ghrepos.presentation.viewmodel.GHReposViewModelFactory
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.DeleteAllGHReposUseCase
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.FetchGHReposUseCase
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.FetchNextGHReposUseCase
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.UpdateGHReposUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideMyViewModelFactory(
        fetchGHReposUseCase: FetchGHReposUseCase,
        fetchNextGHReposUseCase: FetchNextGHReposUseCase,
        updateGHReposUseCase: UpdateGHReposUseCase,
        deleteAllGHReposUseCase: DeleteAllGHReposUseCase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): GHReposViewModelFactory {
        return GHReposViewModelFactory(
            fetchGHReposUseCase,
            fetchNextGHReposUseCase,
            updateGHReposUseCase,
            deleteAllGHReposUseCase,
            ioDispatcher
        )
    }
}