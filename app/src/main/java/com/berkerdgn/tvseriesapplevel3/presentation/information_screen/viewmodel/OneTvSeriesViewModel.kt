package com.berkerdgn.tvseriesapplevel3.presentation.information_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.crewModels.CrewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.episodesModels.EpisodesModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.tvSeriesModels.TvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneTvSeriesViewModel @Inject constructor(
    private val tvSeriesRepositoryImpl: TvSeriesRepositoryImpl
): ViewModel() {

    private val _onTvSeries = MutableLiveData<Resource<TvSeriesModels>>()
    val onTvSeries : LiveData<Resource<TvSeriesModels>>
        get() = _onTvSeries

    private val _oneTvSeriesEpisodes = MutableLiveData<Resource<EpisodesModel>>()
    val onTvShowEpisodesList : LiveData<Resource<EpisodesModel>>
        get() = _oneTvSeriesEpisodes

    private val _oneTvShowCrew = MutableLiveData<Resource<CrewModel>>()
    val oneTvSeriesCrewList : LiveData<Resource<CrewModel>>
        get() = _oneTvShowCrew


    fun getOneTvSeries(idTvSeries:String){
        CoroutineScope(Dispatchers.Main).launch {
          _onTvSeries.value =  tvSeriesRepositoryImpl.getOneTvSeries(idTvSeries)
        }
    }

    fun getTvShowEpisodes(idTvSeries:String){
        CoroutineScope(Dispatchers.Main).launch {
           _oneTvSeriesEpisodes.value = tvSeriesRepositoryImpl.getTvShowEpisodes(idTvSeries)
        }
    }

    fun getTvShowCrews(idTvSeries:String){
        CoroutineScope(Dispatchers.Main).launch {
            _oneTvShowCrew.value = tvSeriesRepositoryImpl.getTvShowCrew(idTvSeries)
        }
    }

}