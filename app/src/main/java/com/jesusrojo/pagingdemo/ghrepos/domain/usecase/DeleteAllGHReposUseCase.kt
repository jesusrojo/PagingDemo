package com.jesusrojo.pagingdemo.ghrepos.domain.usecase

import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository


class DeleteAllGHReposUseCase(private val repository: GHReposRepository) {
    suspend fun execute() = repository.deleteAllGHRepos()
}