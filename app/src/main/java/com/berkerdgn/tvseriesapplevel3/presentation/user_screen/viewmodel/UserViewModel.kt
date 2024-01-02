package com.berkerdgn.tvseriesapplevel3.presentation.user_screen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.for_firebase_model.PostsModel
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private var tvShowRepository: TvSeriesRepository
): ViewModel(){

    var personalPostList = MutableLiveData<List<PostsModel>>()


    fun getPersonalPost(user: String){
        personalPostList = tvShowRepository.getPersonalPost(user)
    }

    fun deletePost(id: String){
        tvShowRepository.deletePost(id)
    }


}