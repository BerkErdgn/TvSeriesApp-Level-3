package com.berkerdgn.tvseriesapplevel3.presentation.episode_screen.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.episodesModels.EpisodesModel
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeViewModel @Inject constructor(
    private val tvSeriesRepositoryImpl: TvSeriesRepositoryImpl
) : ViewModel() {

    private val _episode = MutableLiveData<Resource<EpisodesModel>>()

    val episode : LiveData<Resource<EpisodesModel>>
        get() = _episode


    fun getEpisode(idEpisode:String){
        CoroutineScope(Dispatchers.Main).launch {
            _episode.value = tvSeriesRepositoryImpl.getEpisode(idEpisode)
        }
    }

}