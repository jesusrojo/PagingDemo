package com.jesusrojo.pagingdemo.paging.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jesusrojo.pagingdemo.databinding.ItemLayoutBinding
import com.jesusrojo.pagingdemo.paging.data.model.RepoPaging


class ReposPagingDataAdapter(private val listener: (RepoPaging.Repo) -> Unit) :
    PagingDataAdapter<RepoPaging.Repo, RepoViewHolder>(
        diffCallback = diffUtil
    ) {

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it, position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding){
                 val item = getItem(it)
                if (item != null) { listener(item) }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RepoPaging.Repo>() {

            override fun areItemsTheSame(
                oldItem: RepoPaging.Repo,
                newItem: RepoPaging.Repo
            ): Boolean {
//                return oldItem.repoName == newItem.repoName //todo
                return oldItem.repoId == newItem.repoId
            }

            override fun areContentsTheSame(
                oldItem: RepoPaging.Repo,
                newItem: RepoPaging.Repo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

/////////////////////
//    inner class ReposViewHolder(
//        private val binding: ItemLayoutBinding,
//        listener: (RepoPaging.Repo) -> Unit
//    ): RecyclerView.ViewHolder(binding.root) {
//
//        init {
//            binding.root.setOnClickListener{
//                val position = bindingAdapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    val item = getItem(position)
//                    if (item != null) {
//                        listener(item)
//                    }
//                }
//            }
//        }
//
//        fun onBind(data: RepoPaging.Repo, position: Int) {
//            val avatarUrl = data.avatarUrl
//            if (avatarUrl != null && avatarUrl.isNotEmpty()) {
//                Glide.with(binding.avatarIv.context) // without HILT
//               ////GlideApp.with(binding.ivAvatar.context) // HILT
//                    .load(avatarUrl)
//                    .into(binding.avatarIv)
//            }
//            binding.repoIdTv.text = data.id.toString()
//            binding.repoNameTv.text = data.repoName
//            binding.authorNameTv.text = data.authorName
//            binding.forksCountTv.text = formatNumber(data.forksCount)
//            binding.startsCountTv.text = formatNumber(data.starsCount)
//            val textPosition = "$position ReposViewHolder"
//            binding.positionTv.text = textPosition
//        }
//    }
/////////////////////
}