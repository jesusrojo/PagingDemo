package com.jesusrojo.pagingdemo.ghrepos.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test



class GHRepoTest {

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

    private var sut = GHRepo(repoName, createdAt, description,
        isPrivate, forksCount, starsCount1, openIssuesCount, score,
        authorName, avatarUrl, type, licenseName)

    @Test
    fun getRepoName() {
        assertThat(repoName).isEqualTo(sut.repoName)
    }

    @Test
    fun getCreatedAt() {
        assertThat(createdAt).isEqualTo(sut.createdAt)
    }

    @Test
    fun getDescription() {
        assertThat(description).isEqualTo(sut.description)
    }

    @Test
    fun isPrivate() {
        assertThat(isPrivate).isEqualTo(sut.isPrivate)
    }

    @Test
    fun getForksCount() {
        assertThat(forksCount).isEqualTo(sut.forksCount)
    }

    @Test
    fun getStarsCount() {
        assertThat(starsCount).isEqualTo(sut.starsCount)
    }

    @Test
    fun getOpenIssuesCount() {
        assertThat(openIssuesCount).isEqualTo(sut.openIssuesCount)
    }

    @Test
    fun getAuthorName() {
        assertThat(authorName).isEqualTo(sut.authorName)
    }

    @Test
    fun getAvatarUrl() {
        assertThat(avatarUrl).isEqualTo(sut.avatarUrl)
    }

    @Test
    fun getType() {
        assertThat(type).isEqualTo(sut.type)
    }

    @Test
    fun getLicenseName() {
        assertThat(licenseName).isEqualTo(sut.licenseName)
    }
}