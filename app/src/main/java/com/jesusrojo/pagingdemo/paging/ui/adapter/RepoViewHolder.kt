package com.jesusrojo.pagingdemo.paging.ui.adapter


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jesusrojo.pagingdemo.paging.data.model.RepoPaging
import com.jesusrojo.pagingdemo.databinding.ItemLayoutBinding
import com.jesusrojo.pagingdemo.utils.formatNumber

class RepoViewHolder(
    private val binding: ItemLayoutBinding,
    private val listener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener(position)
            }
        }
    }

    fun onBind(data: RepoPaging.Repo, position: Int) {
        // BIND RepoPaging.Repo
        val avatarUrl = data.avatarUrl
        if (avatarUrl != null && avatarUrl.isNotEmpty()) {
            Glide.with(binding.avatarIv.context) // without HILT
                ////GlideApp.with(binding.ivAvatar.context) // HILT
                .load(avatarUrl)
                .into(binding.avatarIv)
        }
        binding.repoIdTv.text = data.id.toString()
        binding.repoNameTv.text = data.repoName
        binding.authorNameTv.text = data.authorName
        binding.forksCountTv.text = formatNumber(data.forksCount)
        binding.startsCountTv.text = formatNumber(data.starsCount)
        val textPosition = "$position"
        binding.positionTv.text = textPosition
    }
}