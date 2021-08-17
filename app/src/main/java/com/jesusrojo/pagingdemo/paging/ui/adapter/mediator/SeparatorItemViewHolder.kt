package com.jesusrojo.pagingdemo.paging.ui.adapter.mediator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.databinding.ItemSeparatorLayoutBinding

class SeparatorItemViewHolder(
    private val binding: ItemSeparatorLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(status: String) {
        binding.separatorDescription.text = status
    }

    companion object {
        fun create(parent: ViewGroup): SeparatorItemViewHolder {

            val view  = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_separator_layout,parent,false)

            val binding = ItemSeparatorLayoutBinding.bind(view)

            return SeparatorItemViewHolder(binding)
        }
    }
}