package com.jesusrojo.pagingdemo.paging.ui.adapter.mediator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.paging.data.model.RepoEnty
import com.jesusrojo.pagingdemo.databinding.ItemLayoutBinding
import com.jesusrojo.pagingdemo.utils.formatNumber


class RepoEntyViewHolder(
    private val binding: ItemLayoutBinding,
    private val listener: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener(position)
            }
        }
    }

    fun onBind(data: RepoEnty, position: Int) {

        // BIND RepoEnty
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

    companion object {
//        fun create(parent: ViewGroup) : ReposEntyViewHolder {
        fun create(parent: ViewGroup, listener: (Int) -> Unit) : RepoEntyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout,parent,false)
            val binding = ItemLayoutBinding.bind(view)
            return RepoEntyViewHolder(binding, listener)
        }
    }
}