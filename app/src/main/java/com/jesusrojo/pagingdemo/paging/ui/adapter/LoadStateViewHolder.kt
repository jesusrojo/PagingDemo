package com.jesusrojo.pagingdemo.paging.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.databinding.ItemLoadStateFooterLayoutBinding


class LoadStateViewHolder(
    private val binding: ItemLoadStateFooterLayoutBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener {
            retry.invoke()
        }
    }

    fun onBind(loadState: LoadState){
        when (loadState) {
            is LoadState.Loading -> {
                binding.retryButton.isVisible = false
                binding.errorMsg.isVisible = false
                binding.progressBar.isVisible = true
            }
            is LoadState.NotLoading -> {
                binding.retryButton.isVisible = false
                binding.errorMsg.isVisible = false
                binding.progressBar.isVisible = false
            }
            is LoadState.Error -> {
                binding.errorMsg.text = loadState.error.message
                binding.retryButton.isVisible = true
                binding.errorMsg.isVisible = true
                binding.progressBar.isVisible = false
            }
        }
    }

    companion object {

        fun  create(parent: ViewGroup, retry: () -> Unit) : LoadStateViewHolder {

            val view  = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state_footer_layout,parent,false)
            val binding  = ItemLoadStateFooterLayoutBinding.bind(view)

            return  LoadStateViewHolder(binding,retry)
        }
    }
}