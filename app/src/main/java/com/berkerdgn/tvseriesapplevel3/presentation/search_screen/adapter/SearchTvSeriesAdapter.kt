package com.berkerdgn.tvseriesapplevel3.presentation.search_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.tvseriesapplevel3.data.remote.model.searchModels.SearchTvSeriesModelsItem
import com.berkerdgn.tvseriesapplevel3.databinding.AllTvSeriesRawBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class SearchTvSeriesAdapter @Inject constructor(
    private val glide : RequestManager
) : RecyclerView.Adapter<SearchTvSeriesAdapter.SearchTvSeriesViewHolder>() {
    class SearchTvSeriesViewHolder(val view : AllTvSeriesRawBinding ): RecyclerView.ViewHolder(view.root)

    private val diffUtil = object : DiffUtil.ItemCallback<SearchTvSeriesModelsItem>(){
        override fun areItemsTheSame(
            oldItem: SearchTvSeriesModelsItem,
            newItem: SearchTvSeriesModelsItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchTvSeriesModelsItem,
            newItem: SearchTvSeriesModelsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)

    var searchTvSeriesList : List<SearchTvSeriesModelsItem>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTvSeriesViewHolder {
        val binding = AllTvSeriesRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchTvSeriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return searchTvSeriesList.size
    }

    override fun onBindViewHolder(holder: SearchTvSeriesViewHolder, position: Int) {
        val design = holder.view
        val tvShow = searchTvSeriesList[position]

        design.nameText.text= tvShow.show.name
        design.contentText.text = tvShow.show.genres.joinToString(",")

        try {
            glide.load(tvShow.show.image.medium).into(design.showImage)
        }catch (e:Exception){
            e.localizedMessage
        }
    }
}