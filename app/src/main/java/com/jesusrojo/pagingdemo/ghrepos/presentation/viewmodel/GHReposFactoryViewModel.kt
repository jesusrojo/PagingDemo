package com.jesusrojo.pagingdemo.ghrepos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jesusrojo.pagingdemo.ghrepos.domain.usecase.*


import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class GHReposViewModelFactory @Inject constructor(
    private val fetchGHReposUseCase: FetchGHReposUseCase,
    private val fetchNextGHReposUseCase: FetchNextGHReposUseCase,
    private val updateGHReposUseCase: UpdateGHReposUseCase,
    private val deleteAllGHReposUseCase: DeleteAllGHReposUseCase,
    private val ioDispatcher: CoroutineDispatcher
):ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GHReposViewModel(
            fetchGHReposUseCase,
            fetchNextGHReposUseCase,
            updateGHReposUseCase,
            deleteAllGHReposUseCase,
            ioDispatcher
        ) as T
    }
}