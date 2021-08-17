package com.jesusrojo.pagingdemo.ghrepos.domain.usecase

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository

class FetchGHReposUseCase(private val repository: GHReposRepository) {

    suspend fun execute(page: Int): List<GHRepo>? {
        return repository.fetchGHRepos(page)
    }
}