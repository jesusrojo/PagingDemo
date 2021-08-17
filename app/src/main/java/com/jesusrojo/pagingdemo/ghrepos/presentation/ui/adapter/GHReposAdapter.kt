package com.jesusrojo.pagingdemo.ghrepos.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.pagingdemo.databinding.ItemLayoutBinding
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo


class GHReposAdapter() : RecyclerView.Adapter<GHReposViewHolder>() {

    private lateinit var ghReposHolder: GHReposViewHolder
    private lateinit var listener: (GHRepo) -> Unit

    private var values: ArrayList<GHRepo> = arrayListOf()
    //    private var values: List<GHRepo> = emptyList()//todo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GHReposViewHolder {
        val binding = ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        ghReposHolder = GHReposViewHolder(binding, parent.context.resources)
        ghReposHolder.setOnItemClickListener(listener)
        return ghReposHolder
    }

    override fun onBindViewHolder(holder: GHReposViewHolder, position: Int) {
        val item = values[position]
        holder.bindMyHolder(item, position)
    }

    fun setOnItemClickListener(listener: (GHRepo) -> Unit) {
        this.listener = listener
    }

    override fun getItemCount(): Int = values.size

    fun setNewValues(newValues: ArrayList<GHRepo>) {
        values.clear()
       ////values = newValues // bug returning from DetailsFragment
        values.addAll(newValues)
        notifyDataSetChanged()
    }

    fun deleteAll() {
        values.clear()
        notifyDataSetChanged()
    }

    fun orderByName() {
        values.sortWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.repoName })
        )
        notifyDataSetChanged()
    }

    fun orderByStars() {
        values.sortBy { it.starsCount }
        values.reverse()
        notifyDataSetChanged()
    }

    fun orderByForks() {
        values.sortBy { it.forksCount }
        values.reverse()
        notifyDataSetChanged()
    }

    fun orderByAuthorName() {
        values.sortWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.authorName })
        )
        notifyDataSetChanged()
    }
}