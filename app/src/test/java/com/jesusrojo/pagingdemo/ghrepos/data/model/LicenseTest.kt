package com.jesusrojo.pagingdemo.ghrepos.data.model

import com.google.common.truth.Truth
import org.junit.Test

class LicenseTest {
    private val licenseName = "licenseName1"
    private val sut = License(licenseName)

    @Test
    fun getLicenseName() {
        Truth.assertThat(licenseName).isEqualTo(sut.licenseName)
    }
}