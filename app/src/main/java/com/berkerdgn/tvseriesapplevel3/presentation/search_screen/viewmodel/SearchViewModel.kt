package com.berkerdgn.tvseriesapplevel3.presentation.search_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkerdgn.tvseriesapplevel3.data.remote.model.searchModels.SearchTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepository
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val tvShowRepository: TvSeriesRepositoryImpl
) : ViewModel() {

    private val searchTvSeries = MutableLiveData<Resource<SearchTvSeriesModels>>()
    val searchTvSeriesList : LiveData<Resource<SearchTvSeriesModels>>
        get() = searchTvSeries


    fun getSearchTvSeries(q:String){
        CoroutineScope(Dispatchers.Main).launch {
          searchTvSeries.value = tvShowRepository.getSearchTvSeries(q)
        }
    }

}