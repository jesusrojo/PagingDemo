package com.jesusrojo.pagingdemo.ghrepos.domain.usecase

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository

//todo rename NextFetch
class FetchNextGHReposUseCase(private val repository: GHReposRepository) {
  suspend fun execute(pageCount: Int):List<GHRepo>? = repository.fetchNextGHRepos(pageCount)
}