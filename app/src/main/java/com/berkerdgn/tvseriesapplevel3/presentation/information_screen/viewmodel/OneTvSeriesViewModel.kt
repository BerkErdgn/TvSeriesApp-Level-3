package com.berkerdgn.tvseriesapplevel3.presentation.information_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.tvSeriesModels.TvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OneTvSeriesViewModel @Inject constructor(
    private val tvSeriesRepositoryImpl: TvSeriesRepositoryImpl
): ViewModel() {

    private val _onTvSeries = MutableLiveData<Resource<TvSeriesModels>>()

    val onTvSeries : LiveData<Resource<TvSeriesModels>>
        get() = _onTvSeries

    fun getOneTvSeries(idTvSeries:String){
        CoroutineScope(Dispatchers.Main).launch {
            tvSeriesRepositoryImpl.getOneTvSeries(idTvSeries)
        }
    }

}