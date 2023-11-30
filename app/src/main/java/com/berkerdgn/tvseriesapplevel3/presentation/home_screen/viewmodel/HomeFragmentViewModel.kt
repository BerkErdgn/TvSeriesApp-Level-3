package com.berkerdgn.tvseriesapplevel3.presentation.home_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.allTvModels.AllTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.todayModels.TodaysTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val tvSeriesRepository: TvSeriesRepositoryImpl
): ViewModel() {


    private val allTvSeries = MutableLiveData<Resource<AllTvSeriesModels>>()
    val allTvSeriesList : LiveData<Resource<AllTvSeriesModels>>
        get() = allTvSeries

    private val todayTvSeries =MutableLiveData<Resource<TodaysTvSeriesModels>>()
    val todayTvSeriesList : LiveData<Resource<TodaysTvSeriesModels>>
        get() = todayTvSeries


    init {
        getAllTvSeries()
    }

    private fun getAllTvSeries(){
        CoroutineScope(Dispatchers.Main).launch{
            allTvSeries.value = tvSeriesRepository.getAllTvSeries()
        }
    }

    fun getTodayTvSeries(data:String){
        CoroutineScope(Dispatchers.Main).launch {
            todayTvSeries.value = tvSeriesRepository.getTodayTvSeries(data)
        }
    }

}