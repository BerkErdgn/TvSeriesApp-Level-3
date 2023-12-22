package com.berkerdgn.tvseriesapplevel3.presentation.episode_screen.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.episodesModels.EpisodesModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.oneEpisodesModels.OneEpisodesModel
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val tvSeriesRepositoryImpl: TvSeriesRepositoryImpl
) : ViewModel() {

    private val _episode = MutableLiveData<Resource<OneEpisodesModel>>()

    val episode : LiveData<Resource<OneEpisodesModel>>
        get() = _episode


    fun getEpisode(idEpisode:String){
        CoroutineScope(Dispatchers.Main).launch {
            _episode.value = tvSeriesRepositoryImpl.getEpisode(idEpisode)
        }
    }

}