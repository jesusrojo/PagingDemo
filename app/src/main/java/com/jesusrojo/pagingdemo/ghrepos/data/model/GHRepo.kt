package com.jesusrojo.pagingdemo.ghrepos.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ghrepos_table")
data class GHRepo(
    val repoName: String,
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
        return "REPO NAME: $repoName" +
                "\nCREATED AT: $createdAt" +
                "\nDESCRIPTION: $description" +
                "\nIS PRIVATE: $isPrivate" +
                "\nFORKS: $forksCount" +
                "\nSTARS: $starsCount" +
                "\nOPEN ISSUES: $openIssuesCount" +
                "\nSCORE: $score" +
                "\nAUTHOR NAME: $authorName" +
                // "\nAvatarUrl: $avatarUrl)"}" +
                "\nTYPE: $type" +
                "\nLICENSE NAME: $licenseName"
    }
}