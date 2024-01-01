package com.berkerdgn.tvseriesapplevel3.presentation.social_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.tvseriesapplevel3.data.remote.model.for_firebase_model.PostsModel
import com.berkerdgn.tvseriesapplevel3.databinding.CommentRecyclerRawBinding


class PostAdapter : RecyclerView.Adapter<PostAdapter.PostAdapterViewHolder>() {
    class PostAdapterViewHolder (val view : CommentRecyclerRawBinding) : RecyclerView.ViewHolder(view.root)

    private val diffUtil = object : DiffUtil.ItemCallback<PostsModel>(){
        override fun areItemsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
            return oldItem == newItem
        }

    }
    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)
    var postsList : List<PostsModel>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapterViewHolder {
        val binding = CommentRecyclerRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostAdapterViewHolder, position: Int) {
        val design = holder.view
        val post = postsList[position]

        design.commentTextView25.text = post.comment
        design.tvShowNameTextView11.text = post.tvSeriesName
        design.userNameTextView26.text= post.userEmail
    }
}