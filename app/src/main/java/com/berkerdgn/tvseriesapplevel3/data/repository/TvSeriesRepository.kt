package com.berkerdgn.tvseriesapplevel3.data.repository

import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModels
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.util.Resource
import retrofit2.Response

interface TvSeriesRepository {

    suspend fun getAllTvSeries(): Resource<AllTvSeriesModels>


    suspend fun getTodayTvSeries(data: String): Resource<TodaysTvSeriesModels>

}