package com.berkerdgn.tvseriesapplevel3.presentation.social_screen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.for_firebase_model.PostsModel
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor(
    private var tvShowRepository: TvSeriesRepositoryImpl
): ViewModel() {

    var postsList = MutableLiveData<List<PostsModel>>()

    init {
        getPosts()
    }

    private fun getPosts() {
       postsList =  tvShowRepository.getPosts()
    }

}