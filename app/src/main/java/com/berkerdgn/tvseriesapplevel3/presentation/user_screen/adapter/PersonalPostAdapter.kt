package com.berkerdgn.tvseriesapplevel3.presentation.user_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.tvseriesapplevel3.data.remote.model.for_firebase_model.PostsModel
import com.berkerdgn.tvseriesapplevel3.databinding.PersonalCommentRecyclerRawBinding
import com.berkerdgn.tvseriesapplevel3.presentation.user_screen.viewmodel.UserViewModel

class PersonalPostAdapter (var userViewModel: UserViewModel) : RecyclerView.Adapter<PersonalPostAdapter.PersonalPostViewHolder>()  {

    class PersonalPostViewHolder(val view : PersonalCommentRecyclerRawBinding): RecyclerView.ViewHolder(view.root)

    private val diffUtil = object  : DiffUtil.ItemCallback<PostsModel>(){
        override fun areItemsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)

    var personalPostList : List<PostsModel>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalPostViewHolder {
        val binding = PersonalCommentRecyclerRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonalPostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return personalPostList.size
    }

    override fun onBindViewHolder(holder: PersonalPostViewHolder, position: Int) {
        val design = holder.view
        val post = personalPostList[position]

        design.deleteImageView.setOnClickListener {
            userViewModel.deletePost(post.id!!)
        }

        design.personalCommentTextView25.text = post.comment
        design.personalTvShowNameTextView11.text = post.tvSeriesName
        design.personalUserNameTextView26.text = post.userEmail

    }
}