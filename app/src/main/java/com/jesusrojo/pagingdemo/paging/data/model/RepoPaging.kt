package com.jesusrojo.pagingdemo.paging.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class RepoPaging(
    val totalPage: Int = 0,
    val currentPage: Int = 0,
    val repos: List<Repo>
) {
    val endOfPage = totalPage == currentPage

    @Entity(tableName = "repo_table")
    data class Repo(
        val repoId: Long,
        val repoName: String,
        val language: String,
        val createdAt: String,
        val description: String,
        val isPrivate: Boolean,
        val forksCount: Int,
        val starsCount: Int,
        val openIssuesCount: Int,
        val score: Double,
        val authorName: String,
        val avatarUrl: String,
        val type: String,
        val licenseName: String
    ) : Serializable {

        @PrimaryKey(autoGenerate = true) var id: Long = 0

        override fun toString(): String {
            return "ID: $id" +
                    "\nREPO ID: $repoId" +
                    "\nREPO NAME: $repoName" +
                    "\nLANGUAGE: $language" +
                    "\nCREATED AT: $createdAt" +
                    "\nDESCRIPTION: $description" +
                    "\nIS PRIVATE: $isPrivate" +
                    "\nFORKS: $forksCount" +
                    "\nSTARS: $starsCount" +
                    "\nOPEN ISSUES: $openIssuesCount" +
                    "\nSCORE: $score" +
                    "\nAUTHOR NAME: $authorName" +
                    "\nAVATAR URL: $avatarUrl)" +
                    "\nTYPE: $type" +
                    "\nLICENSE NAME: $licenseName"
        }
    }

//    @Keep
//    data class Repo(
//        @SerializedName("repoName") val repoName: String,
//        @SerializedName("createdAt") val createdAt: String,
//        @SerializedName("description") val description: String,
//        @SerializedName("isPrivate") val isPrivate: Boolean,
//        @SerializedName("forksCount") val forksCount: Int,
//        @SerializedName("starsCount") val starsCount: Int,
//        @SerializedName("openIssuesCount") val openIssuesCount: Int,
//        @SerializedName("score") val score: Double,
//        @SerializedName("authorName") val authorName: String,
//        @SerializedName("avatarUrl") val avatarUrl: String,
//        @SerializedName("type") val type: String,
//        @SerializedName("licenseName") val licenseName: String
//    )
}