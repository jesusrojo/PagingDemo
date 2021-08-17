package com.jesusrojo.pagingdemo.ghrepos.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class GHReposListTest {

    private val repoName = "repoName1"
    private val createdAt = "createdAt1"
    private val description = "description1"
    private val isPrivate = false
    private val forksCount = 1
    private val starsCount = 1
    private val starsCount1 = starsCount
    private val openIssuesCount = 1
    private val score = 1.0
    private val authorName = "authorName1"
    private val avatarUrl = "avatarUrl1"
    private val type = "type1"
    private val licenseName = "licenseName1"

    private val owner = Owner(authorName, avatarUrl, type)
    private val license = License(licenseName)

    private var ghRepoRaw = GHRepoRaw(repoName, createdAt, description,
        isPrivate, forksCount, starsCount1, openIssuesCount, score,
        owner, license)

    private val items: List<GHRepoRaw> = ArrayList()
    private var sut = GHReposList(items)

    @Test
    fun getItems_containItems() {
        assertThat(items).isEqualTo(sut.items)
    }

    @Test
    fun getItems_containItems2() {
        val items: List<GHRepoRaw> = mutableListOf()
        val sut = GHReposList(items)
        assertThat(items).isEqualTo(sut.items)
    }

    @Test
    fun getItems_containItems3() {
        val items: List<GHRepoRaw> = mutableListOf(ghRepoRaw)
        val sut = GHReposList(items)
        assertThat(items).isEqualTo(sut.items)
    }
}