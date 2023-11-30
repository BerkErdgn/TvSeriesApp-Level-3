package com.berkerdgn.tvseriesapplevel3.presentation.home_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.tvseriesapplevel3.data.remote.model.todayModels.TodaysTvSeriesModelsItem
import com.berkerdgn.tvseriesapplevel3.databinding.TodayImageRawBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class TodayTvSeriesAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<TodayTvSeriesAdapter.TodayTvSeriesViewHolder>() {
    class TodayTvSeriesViewHolder(var view: TodayImageRawBinding): RecyclerView.ViewHolder(view.root)


    private val diffUtil = object : DiffUtil.ItemCallback<TodaysTvSeriesModelsItem>(){
        override fun areItemsTheSame(
            oldItem: TodaysTvSeriesModelsItem,
            newItem: TodaysTvSeriesModelsItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TodaysTvSeriesModelsItem,
            newItem: TodaysTvSeriesModelsItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)

    var todayTvSeriesList : List<TodaysTvSeriesModelsItem>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayTvSeriesViewHolder {
        val binding = TodayImageRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodayTvSeriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todayTvSeriesList.size
    }

    override fun onBindViewHolder(holder: TodayTvSeriesViewHolder, position: Int) {
        val design = holder.view
        val tvShow = todayTvSeriesList[position]

        val imageView = design.todayImage

        try {
            glide.load(tvShow._embedded.show.image.original).into(imageView)
        }catch (e:Exception){

        }


    }
}