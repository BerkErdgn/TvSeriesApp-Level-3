package com.berkerdgn.tvseriesapplevel3.data.remote

import androidx.lifecycle.MutableLiveData
import com.berkerdgn.tvseriesapplevel3.data.remote.model.for_firebase_model.PostsModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.toObject

class PostDataSource (var collectionPost: CollectionReference){

    var postsList = MutableLiveData<List<PostsModel>>()
    fun getPosts(): MutableLiveData<List<PostsModel>>{
        collectionPost.addSnapshotListener { value, error ->
            if(value !=null){
                val list = ArrayList<PostsModel>()
                for (d in value.documents){
                    val post = d.toObject(PostsModel::class.java)
                    if (post != null){
                        post.id = d.id
                        list.add(post)
                    }
                }
                postsList.value = list
            }
        }
        return postsList
    }

    fun getPersonalPost(user: String): MutableLiveData<List<PostsModel>>{
        collectionPost.addSnapshotListener { value, error ->
            if(value !=null){
                val list = ArrayList<PostsModel>()
                for (d in value.documents){
                    val post = d.toObject(PostsModel::class.java)
                    if (post != null){
                        if(post.userEmail == user){
                            post.id = d.id
                            list.add(post)
                        }

                    }
                }
                postsList.value = list
            }
        }
        return postsList
    }


    fun uploadPosts(comment: String,date: String,tvSeriesName:String,userEmail:String){
        val newPost = PostsModel("",comment, date, tvSeriesName, userEmail)
        collectionPost.document().set(newPost)
    }

    fun deletePost(id: String){
        collectionPost.document(id).delete()
    }


}