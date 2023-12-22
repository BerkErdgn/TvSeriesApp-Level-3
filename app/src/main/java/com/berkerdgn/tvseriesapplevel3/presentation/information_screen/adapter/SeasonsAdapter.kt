package com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.tvseriesapplevel3.data.remote.model.episodesModels.EpisodesModelItem
import com.berkerdgn.tvseriesapplevel3.databinding.SeasonsRecyclerRawBinding
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.OneTvSeriesFragmentDirections
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class SeasonsAdapter @Inject constructor(
    private val glide : RequestManager
) : RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder>() {
    class SeasonsViewHolder(val view: SeasonsRecyclerRawBinding): RecyclerView.ViewHolder(view.root)

    private val diffUtil = object : DiffUtil.ItemCallback<EpisodesModelItem>(){
        override fun areItemsTheSame(
            oldItem: EpisodesModelItem,
            newItem: EpisodesModelItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: EpisodesModelItem,
            newItem: EpisodesModelItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)

    var seasonsList : List<EpisodesModelItem>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
       val binding = SeasonsRecyclerRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SeasonsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return seasonsList.size
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        val design = holder.view
        val oneSeason = seasonsList[position]

        design.seasonsNameTextView10.text = oneSeason.name

        design.seasonsConstraintLayout.setOnClickListener {
            val action = OneTvSeriesFragmentDirections.actionOneTvSeriesFragmentToEpisodeFragment(oneSeason.id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        try {
            glide.load(oneSeason.image.medium).into(design.seasonsImageView)
        }catch (e:Exception){
            println(e.localizedMessage)
        }

    }
}