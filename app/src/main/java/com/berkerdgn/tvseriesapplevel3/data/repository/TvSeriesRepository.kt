package com.berkerdgn.tvseriesapplevel3.data.repository

import com.berkerdgn.tvseriesapplevel3.data.remote.model.allTvModels.AllTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.crewModels.CrewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.episodesModels.EpisodesModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.searchModels.SearchTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.todayModels.TodaysTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.tvSeriesModels.TvSeriesModels
import com.berkerdgn.tvseriesapplevel3.util.Resource

interface TvSeriesRepository {

    suspend fun getAllTvSeries(): Resource<AllTvSeriesModels>


    suspend fun getTodayTvSeries(data: String): Resource<TodaysTvSeriesModels>

    suspend fun getSearchTvSeries(q: String): Resource<SearchTvSeriesModels>

    suspend fun getOneTvSeries(idTvSeries: String): Resource<TvSeriesModels>

    suspend fun getTvShowEpisodes(idTvSeries: String): Resource<EpisodesModel>

    suspend fun getTvShowCrew(idTvSeries: String): Resource<CrewModel>

}