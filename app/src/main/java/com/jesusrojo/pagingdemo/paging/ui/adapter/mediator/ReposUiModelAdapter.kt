package com.jesusrojo.pagingdemo.paging.ui.adapter.mediator

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.paging.data.model.RepoEnty
import com.jesusrojo.pagingdemo.paging.data.model.UiModel
import java.lang.UnsupportedOperationException


class ReposUiModelAdapter(private val listener: (RepoEnty) -> Unit) :
    PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(
        diffCallback = diffUtil
    ) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (it) {
                is UiModel.RepoItem -> (holder as RepoEntyViewHolder).onBind(it.enty, position)
                is UiModel.SeparatorItem -> (holder as SeparatorItemViewHolder).onBind(it.status)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.item_layout) {
           //// ReposEntyViewHolder.create(parent)
            RepoEntyViewHolder.create(parent) { onClickListenerViewHolder(it) }
        } else {
            SeparatorItemViewHolder.create(parent)
        }
    }

    private fun onClickListenerViewHolder(position: Int) {
        when (val itemX = getItem(position)) {
            is UiModel.RepoItem -> listener(itemX.enty)
            is UiModel.SeparatorItem -> { /*nothing*/
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.RepoItem -> R.layout.item_layout
            is UiModel.SeparatorItem -> R.layout.item_separator_layout
            else -> throw UnsupportedOperationException("Unknown view type")
        }
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UiModel>() {

            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.RepoItem && newItem is UiModel.RepoItem) &&
                        oldItem.enty.repoId == newItem.enty.repoId ||
                        (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem) &&
                        oldItem.status == newItem.status
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}