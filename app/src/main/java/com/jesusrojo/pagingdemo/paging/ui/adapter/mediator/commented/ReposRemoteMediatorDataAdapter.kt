@file:Suppress("PackageDirectoryMismatch")
//package com.jesusrojo.reposclient.paging.ui.adapter.mediator
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.paging.PagingDataAdapter
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.jesusrojo.reposclient.databinding.ItemLayoutBinding
//import com.jesusrojo.reposclient.paging.data.model.RepoEnty
//
//
//class ReposRemoteMediatorDataAdapter() :
//    PagingDataAdapter<RepoEnty, ReposRemoteMediatorDataAdapter.ReposMediatorViewHolder>(
//        diffCallback = diffUtil
//    ) {
//
//    override fun onBindViewHolder(holder: ReposMediatorViewHolder, position: Int) {
//        getItem(position)?.let {
//            holder.onBind(it, position)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposMediatorViewHolder {
//        val binding = ItemLayoutBinding
//            .inflate(LayoutInflater.from(parent.context), parent, false)
//
//        return  ReposMediatorViewHolder(binding)
//    }
//
//    inner class ReposMediatorViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        fun onBind(data: RepoEnty, position: Int) {
//            binding.lbPosition.text = position.toString()
//            binding.lbTaskId.text = "ReposMediatorViewHolder"
//            binding.lbUserId.text = data.repoName
//            binding.lbTitle.text = data.toString()
//        }
//    }
//
//    companion object {
//        val diffUtil = object : DiffUtil.ItemCallback<RepoEnty>() {
//
//            override fun areItemsTheSame(oldItem: RepoEnty,
//                                         newItem: RepoEnty): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//            override fun areContentsTheSame(oldItem: RepoEnty,
//                                            newItem: RepoEnty): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//}