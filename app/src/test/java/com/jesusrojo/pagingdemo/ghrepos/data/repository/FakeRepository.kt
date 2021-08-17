package com.jesusrojo.pagingdemo.ghrepos.data.repository

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepoRaw
import com.jesusrojo.pagingdemo.ghrepos.data.model.License
import com.jesusrojo.pagingdemo.ghrepos.data.model.Owner
import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository


class FakeRepository : GHReposRepository {

    private var datas = mutableListOf<GHRepo>()

    init {
        datas = getFakeListItemsOneTwo().toMutableList()
    }

    override suspend fun fetchGHRepos(page: Int): List<GHRepo>? {
        return datas
    }

    override suspend fun fetchNextGHRepos(pageCount: Int): List<GHRepo>? {
        return datas
    }

    override suspend fun updateGHRepos(): List<GHRepo>? {
        datas.clear()
        datas = getFakeListItemsThreeFour().toMutableList()
        return datas
    }

    override suspend fun deleteAllGHRepos() {
        datas.clear()
    }


    companion object {

        fun getFakeListItemsOneTwo(): List<GHRepo> {
            return listOf(
                GHRepo(
                    "repoName1", "createdAt1", "description1",
                    false, 1, 1, 1, 1.0,
                    "authorName1", "avatarUrl1", "type1", "licenseName1"
                ), GHRepo(
                    "repoName2", "createdAt2", "description2",
                    false, 2, 2, 2, 2.0,
                    "authorName2", "avatarUrl2", "type2", "licenseName2"
                )
            )
        }

        fun getFakeListItemsThreeFour(): List<GHRepo> {
            val datas = mutableListOf<GHRepo>()
            datas.add(
                GHRepo(
                    "repoName3", "createdAt3", "description3",
                    false, 3, 3, 3, 3.0,
                    "authorName3", "avatarUrl3", "type3", "licenseName3"
                )
            )
            datas.add(
                GHRepo(
                    "repoName4", "createdAt4", "description4",
                    false, 4, 4, 4, 4.0,
                    "authorName4", "avatarUrl4", "type4", "licenseName4"
                )
            )
            return datas
        }


        fun getFakeListGHRepoRawItemsOneTwo(): List<GHRepoRaw> {
            return listOf<GHRepoRaw>(
                GHRepoRaw(
                    "repoName1", "createdAt1", "description1",
                    false, 1, 1, 1, 1.0,
                    Owner("authorName1", "avatarUrl1", "type1"),
                    License("licenseName1")
                ),
                GHRepoRaw(
                    "repoName2", "createdAt2", "description2",
                    false, 2, 2, 2, 2.0,
                    Owner("authorName2", "avatarUrl2", "type2"),
                    License("licenseName2")
                )
            )
        }
    }
}