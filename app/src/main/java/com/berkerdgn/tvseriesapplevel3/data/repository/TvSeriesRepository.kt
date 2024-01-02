package com.berkerdgn.tvseriesapplevel3.data.repository

import androidx.lifecycle.MutableLiveData
import com.berkerdgn.tvseriesapplevel3.data.remote.model.allTvModels.AllTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.crewModels.CrewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.episodesModels.EpisodesModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.for_firebase_model.PostsModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.oneEpisodesModels.OneEpisodesModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.peopleModels.PeopleModel
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

    suspend fun getPeople(idPeople: String): Resource<PeopleModel>

    suspend fun getEpisode(idEpisode:String): Resource<OneEpisodesModel>

    fun getPosts(): MutableLiveData<List<PostsModel>>

    fun uploadPosts(comment: String,date: String,tvSeriesName:String,userEmail:String)

    fun getPersonalPost(user: String): MutableLiveData<List<PostsModel>>

    fun deletePost(id: String)

}