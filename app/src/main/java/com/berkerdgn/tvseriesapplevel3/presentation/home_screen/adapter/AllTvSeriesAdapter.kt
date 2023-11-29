package com.berkerdgn.tvseriesapplevel3.presentation.home_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModelsItem
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.databinding.AllTvSeriesRawBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class AllTvSeriesAdapter @Inject constructor(
    val glide: RequestManager
): RecyclerView.Adapter<AllTvSeriesAdapter.AllTvSeriesViewHolder>(){
    class AllTvSeriesViewHolder (val view : AllTvSeriesRawBinding) : RecyclerView.ViewHolder(view.root)

    private val diffUtil = object : DiffUtil.ItemCallback<AllTvSeriesModelsItem>(){
        override fun areItemsTheSame(
            oldItem: AllTvSeriesModelsItem,
            newItem: AllTvSeriesModelsItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AllTvSeriesModelsItem,
            newItem: AllTvSeriesModelsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)

    var allTvShowList : List<AllTvSeriesModelsItem>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTvSeriesViewHolder {
        val binding = AllTvSeriesRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AllTvSeriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return allTvShowList.size
    }

    override fun onBindViewHolder(holder: AllTvSeriesViewHolder, position: Int) {
        val design = holder.view
        val tvShow = allTvShowList[position]

        design.nameText.text= tvShow.name
        design.contentText.text = tvShow.genres.joinToString (",")

        try {
            glide.load(tvShow.image.medium).into(design.showImage)
        }catch (e:Exception){
            e.localizedMessage
        }


    }
}