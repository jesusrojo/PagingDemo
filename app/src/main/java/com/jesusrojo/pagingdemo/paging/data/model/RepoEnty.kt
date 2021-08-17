package com.jesusrojo.pagingdemo.paging.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "repo_enty_table")
data class RepoEnty(
//    @PrimaryKey @field:SerializedName("id") val repoId: Long,
    val repoId: Long?,
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