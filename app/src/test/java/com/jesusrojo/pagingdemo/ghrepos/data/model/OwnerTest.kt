package com.jesusrojo.pagingdemo.ghrepos.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class OwnerTest {

    private val authorName = "authorName1"
    private val avatarUrl = "avatarUrl1"
    private val type = "type1"
    private val sut = Owner(authorName, avatarUrl, type)

    @Test
    fun getAuthorName() {
        assertThat(authorName).isEqualTo(sut.authorName)
    }

    @Test
    fun setAuthorName() {
        assertThat(authorName).isEqualTo(sut.authorName)

        val changedNamed = "changedName"
        sut.authorName = changedNamed

        assertThat(changedNamed).isEqualTo(sut.authorName)
    }

    @Test
    fun getAvatarUrl() {
        assertThat(avatarUrl).isEqualTo(sut.avatarUrl)
    }

    @Test
    fun getType() {
        assertThat(type).isEqualTo(sut.type)
    }
}