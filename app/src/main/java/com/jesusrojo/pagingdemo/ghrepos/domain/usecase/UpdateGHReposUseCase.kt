package com.jesusrojo.pagingdemo.ghrepos.domain.usecase

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository


class UpdateGHReposUseCase(private val repository: GHReposRepository) {
    suspend fun execute():List<GHRepo>? = repository.updateGHRepos()
}