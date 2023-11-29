package com.berkerdgn.tvseriesapplevel3.data.remote


import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModels
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import retrofit2.Response
import retrofit2.http.GET
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

}