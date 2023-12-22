package com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.tvseriesapplevel3.data.remote.model.crewModels.CrewModelItem
import com.berkerdgn.tvseriesapplevel3.databinding.PersonRawBinding
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.OneTvSeriesFragmentDirections
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class CrewAdapter @Inject constructor(
    private val glide : RequestManager
): RecyclerView.Adapter<CrewAdapter.CrewViewHolder>(){
    class CrewViewHolder(val view : PersonRawBinding): RecyclerView.ViewHolder(view.root)

    private val diffUtil = object : DiffUtil.ItemCallback<CrewModelItem>(){
        override fun areItemsTheSame(oldItem: CrewModelItem, newItem: CrewModelItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CrewModelItem, newItem: CrewModelItem): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)

    var crewList : List<CrewModelItem>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val binding = PersonRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CrewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return crewList.size
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        val design = holder.view
        val onePerson = crewList[position]

        design.firstNameTextView.text = onePerson.person.name
        design.secondNameTextView.text = onePerson.person.name

        design.personConstraintLayout.setOnClickListener {
            val action = OneTvSeriesFragmentDirections.actionOneTvSeriesFragmentToPeopleFragment(onePerson.person.id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        try {
            glide.load(onePerson.person.image.medium).into(design.imageView)
        }catch (e:Exception){
            println(e.localizedMessage)
        }

    }
}