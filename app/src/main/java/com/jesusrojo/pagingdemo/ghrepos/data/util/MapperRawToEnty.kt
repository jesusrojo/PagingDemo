package com.jesusrojo.pagingdemo.ghrepos.data.util

import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepoRaw


class MapperRawToEnty: Function1<List<GHRepoRaw>, List<GHRepo>> {

    override fun invoke(rawDatas: List<GHRepoRaw>): List<GHRepo> {
        return rawDatas.map {
//            Timber.d("GHRepoRaw.toString $it ##")
            var repoName = ""
            var createdAt = ""
            var description = ""
            var isPrivate = false
            var forksCount = -1
            var starsCount= -1
            var openIssues: Int= -1
            var score: Double = -1.0
            var authorName = ""
            var avatarUrl = ""
            var type = ""
            var licenseName = ""

            val nameRaw = it.repoName
            if(nameRaw != null) repoName = nameRaw

            val createAtRaw = it.createdAt
            if(createAtRaw != null) createdAt = createAtRaw

            val descriptionRaw = it.description
            if(descriptionRaw != null) description = descriptionRaw

            val isPrivateRaw = it.isPrivate
            if(isPrivateRaw != null) isPrivate = isPrivateRaw

            val forksCountRaw = it.forksCount
            if(forksCountRaw != null) forksCount = forksCountRaw

            val starsCountRaw = it.starsCount
            if(starsCountRaw != null) starsCount = starsCountRaw
            
            val openIssuesRaw = it.openIssuesCount
            if(openIssuesRaw != null) openIssues = openIssuesRaw

            val scoreRaw = it.score
            if(scoreRaw != null) score = scoreRaw

            val authorNameRaw = it.owner?.authorName
            if(authorNameRaw != null) authorName = authorNameRaw

            val avatarUrlRaw = it.owner?.avatarUrl
            if(avatarUrlRaw != null) avatarUrl = avatarUrlRaw

            val typeRaw = it.owner?.type
            if(typeRaw != null) type = typeRaw

            val licenseNameRaw = it.license?.licenseName
            if(licenseNameRaw != null) licenseName = licenseNameRaw

            GHRepo(
                repoName,
                createdAt,
                description,
                isPrivate,
                forksCount,
                starsCount,
                openIssues,
                score,
                authorName,
                avatarUrl,
                type,
                licenseName
            )
        }
    }
}