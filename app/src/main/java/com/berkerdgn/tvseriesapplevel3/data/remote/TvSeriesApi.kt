package com.berkerdgn.tvseriesapplevel3.data.remote



import com.berkerdgn.tvseriesapplevel3.data.remote.model.allTvModels.AllTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.crewModels.CrewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.episodesModels.EpisodesModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.searchModels.SearchTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.todayModels.TodaysTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.tvSeriesModels.TvSeriesModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvSeriesApi {

    /*

    https://api.tvmaze.com/schedule/web?date=2023-01-30
    https://api.tvmaze.com/shows
    https://api.tvmaze.com/search/shows?q=girls
    https://api.tvmaze.com/shows/1?embed=cast
    https://api.tvmaze.com/seasons/1/episodes
    https://api.tvmaze.com/shows/1/crew
    https://api.tvmaze.com/people/1
    https://api.tvmaze.com/episodes/51

     */

    @GET("shows")
    suspend fun allTvSeries():Response<AllTvSeriesModels>

    @GET("schedule/web")
    suspend fun getTodayTvSeries(@Query("data") data : String) : Response<TodaysTvSeriesModels>

    @GET("search/shows")
    suspend fun getSearchTvSeries(@Query("q") q : String) : Response<SearchTvSeriesModels>

    @GET("shows/{idTvSeries}?embed=cast")
    suspend fun getOneTvSeries(@Path("idTvSeries")idTvSeries: String) : Response<TvSeriesModels>

    @GET("seasons/{idTvSeries}/episodes")
    suspend fun getTvShowEpisodes(@Path("idTvSeries")idTvSeries :String) : Response<EpisodesModel>

    @GET("shows/{idTvSeries}/crew")
    suspend fun getTvShowCrew(@Path("idTvSeries")idTvSeries:String): Response<CrewModel>

}