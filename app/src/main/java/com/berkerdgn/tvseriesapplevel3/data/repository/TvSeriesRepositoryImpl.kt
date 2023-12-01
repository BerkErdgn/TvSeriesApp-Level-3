package com.berkerdgn.tvseriesapplevel3.data.repository


import com.berkerdgn.tvseriesapplevel3.data.remote.model.todayModels.TodaysTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.TvSeriesApi
import com.berkerdgn.tvseriesapplevel3.data.remote.model.allTvModels.AllTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.searchModels.SearchTvSeriesModels
import com.berkerdgn.tvseriesapplevel3.data.remote.model.tvSeriesModels.TvSeriesModels
import com.berkerdgn.tvseriesapplevel3.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    override suspend fun getSearchTvSeries(q: String): Resource<SearchTvSeriesModels> =
        withContext(Dispatchers.IO){
            return@withContext try{
                val response = tvSeriesApi.getSearchTvSeries(q)
                if (response.isSuccessful){
                    response.body()?.let{
                        return@let Resource.success(it)
                    }?: Resource.error("No data", data = null)
                }else{
                    Resource.error("Error in search", data = null)
                }
            }catch (e:Exception){
                Resource.error(e.localizedMessage,data = null)
            }
        }

    override suspend fun getOneTvSeries(idTvSeries: String): Resource<TvSeriesModels> =
        withContext(Dispatchers.IO){
            return@withContext try {
                val response = tvSeriesApi.getOneTvSeries(idTvSeries)
                if (response.isSuccessful){
                    response.body()?.let {
                        return@let Resource.success(it)
                    }?: Resource.error("Error",data = null)
                }else{
                    Resource.error("Error", data = null)
                }
            }catch (e:Exception){
                Resource.error("Error", data = null)
            }
        }

}

