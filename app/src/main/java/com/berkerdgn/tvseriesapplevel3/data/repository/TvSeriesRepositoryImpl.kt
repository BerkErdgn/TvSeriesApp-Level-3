package com.berkerdgn.tvseriesapplevel3.data.repository

import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModels
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.TvSeriesApi
import com.berkerdgn.tvseriesapplevel3.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class TvSeriesRepositoryImpl @Inject constructor(
    private val tvSeriesApi: TvSeriesApi
) : TvSeriesRepository {
    override suspend fun getAllTvSeries(): Resource<AllTvSeriesModels> =
        withContext(Dispatchers.IO){
            return@withContext try {
                val response = tvSeriesApi.allTvSeries()
                if (response.isSuccessful){
                    response.body()?.let {
                        return@let Resource.success(it)
                    }?: Resource.error("No data could be retrieved!!", data = null)
                }else{
                    Resource.error("No data could be retrieved!!", data = null)
                }
            }catch (e: Exception){
                Resource.error(e.localizedMessage, data = null)
            }
        }

    override suspend fun getTodayTvSeries(data: String): Resource<TodaysTvSeriesModels> =
        withContext(Dispatchers.IO){
            return@withContext try {
                val response = tvSeriesApi.getTodayTvSeries(data)
                if (response.isSuccessful){
                    response.body()?.let {
                        return@let Resource.success(it)
                    }?: Resource.error("No data",data = null)
                }else{
                    Resource.error("No data",data = null)
                }
            }catch (e:Exception){
                Resource.error(e.localizedMessage,data = null)
            }
        }

}

