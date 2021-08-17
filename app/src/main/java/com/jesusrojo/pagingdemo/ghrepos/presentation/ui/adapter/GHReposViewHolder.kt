package com.jesusrojo.pagingdemo.ghrepos.presentation.ui.adapter

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.databinding.ItemLayoutBinding
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.presentation.hilt.GlideApp
import com.jesusrojo.pagingdemo.utils.formatNumber

class GHReposViewHolder(
    private val binding: ItemLayoutBinding,
    resources: Resources?
): RecyclerView.ViewHolder(binding.root) {

    private var name =  resources?.getString(R.string.repo_name)
    private var starsCount = resources?.getString(R.string.starts_count)
    private var forksCount = resources?.getString(R.string.forks_count)
    private var authorName = resources?.getString(R.string.author_name)

    private var onItemClickListener: ((GHRepo) -> Unit)? = null

    fun setOnItemClickListener(listener: (GHRepo) -> Unit) {
        onItemClickListener = listener
    }

    fun bindMyHolder(entyData: GHRepo, position: Int) {

        binding.root.setOnClickListener {
            onItemClickListener?.let {
                it(entyData)
            }
        }

        val repoName = entyData.repoName

        val repoNameUi = "$name: $repoName"
        val startsCountUi = "$starsCount:  ${formatNumber(entyData.starsCount)}"
        val forksCountUi = "$forksCount:  ${formatNumber(entyData.forksCount)}"
        val authorNameUi = "$authorName:  ${entyData.authorName}"
        val avatarUrl = entyData.avatarUrl

        binding.repoNameTv.text = repoNameUi
        binding.startsCountTv.text = startsCountUi
        binding.forksCountTv.text = forksCountUi
        binding.authorNameTv.text = authorNameUi
        binding.positionTv.text = position.toString()

        if (avatarUrl != null && avatarUrl.isNotEmpty()) {
            // Glide.with(binding.ivAvatar.context) // without HILT //todo
            GlideApp.with(binding.avatarIv.context) // with HILT
                .load(avatarUrl)
                .into(binding.avatarIv)
        }
    }
}