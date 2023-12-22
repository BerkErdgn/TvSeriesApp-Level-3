package com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.tvseriesapplevel3.data.remote.model.tvSeriesModels.Cast
import com.berkerdgn.tvseriesapplevel3.databinding.PersonRawBinding
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.OneTvSeriesFragmentDirections
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class CastAdapter @Inject constructor(
    private val glide : RequestManager
): RecyclerView.Adapter<CastAdapter.CastViewHolder>(){
    class CastViewHolder(val view : PersonRawBinding): RecyclerView.ViewHolder(view.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Cast>(){
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)

    var castList : List<Cast>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = PersonRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val design = holder.view
        val onePerson = castList[position]

        design.firstNameTextView.text = onePerson.person.name
        design.secondNameTextView.text = onePerson.character.name

        design.personConstraintLayout.setOnClickListener {
            val action = OneTvSeriesFragmentDirections.actionOneTvSeriesFragmentToPeopleFragment(onePerson.person.id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        try {
            glide.load(onePerson.person.image.medium).into(design.imageView)
        }catch (e:Exception){

        }

    }
}