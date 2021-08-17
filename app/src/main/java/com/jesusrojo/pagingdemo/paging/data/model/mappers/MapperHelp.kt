package com.jesusrojo.pagingdemo.paging.data.model.mappers

import com.jesusrojo.pagingdemo.paging.data.model.RepoEnty
import com.jesusrojo.pagingdemo.paging.data.model.RepoPaging
import com.jesusrojo.pagingdemo.paging.data.model.ReposResponse
import com.jesusrojo.pagingdemo.utils.DebugHelp

// todo
class MapperHelp {
    companion object {

         fun mapFromResponse(response: ReposResponse, mCurrentPage: Int): RepoPaging {
            DebugHelp.l("mapFromResponse")
            return with(response) {
                RepoPaging(
                    totalPage = total,
                    currentPage = mCurrentPage,
                    repos = items.map {
                        // DebugHelp.l("map... ${it.toString()} ##")
                        var repoId = 0L
                        var repoName = ""
                        var language = ""
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

                        val repoIdRaw = it.repoId
                        if(repoIdRaw != null) repoId = repoIdRaw

                        val nameRaw = it.repoName
                        if(nameRaw != null) repoName = nameRaw

                        val languageRaw = it.language
                        if(languageRaw != null) language = languageRaw

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

                        RepoPaging.Repo(
                            repoId,
                            repoName,
                            language,
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
                )
            }
        }


        fun mapToEntity(model: List<RepoPaging.Repo>): List<RepoEnty> {
            return model.map {
                RepoEnty(
                    repoId = it.repoId,
                    repoName = it.repoName,
                    language = it.language,
                    createdAt= it.createdAt,
                    description= it.description,
                    isPrivate= it.isPrivate,
                    forksCount= it.forksCount,
                    starsCount= it.starsCount,
                    openIssuesCount= it.openIssuesCount,
                    score= it.score,
                    authorName= it.authorName,
                    avatarUrl= it.avatarUrl,
                    type= it.type,
                    licenseName= it.licenseName
                )
            }
        }



    }
}