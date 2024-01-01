package com.berkerdgn.tvseriesapplevel3.presentation.add_post_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private var tvShowRepository: TvSeriesRepositoryImpl
) : ViewModel() {

    fun uploadPosts(
        comment: String,
        date: String,
        tvSeriesName: String,
        userEmail: String)
    {
       tvShowRepository.uploadPosts(comment, date, tvSeriesName, userEmail)
    }

}