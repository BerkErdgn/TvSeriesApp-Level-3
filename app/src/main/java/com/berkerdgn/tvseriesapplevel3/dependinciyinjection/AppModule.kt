package com.berkerdgn.tvseriesapplevel3.dependinciyinjection

import android.content.Context
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.data.remote.PostDataSource
import com.berkerdgn.tvseriesapplevel3.data.remote.TvSeriesApi
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepository
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.util.Constants.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTvSeriesRepository(tvSeriesApi: TvSeriesApi, pds:PostDataSource): TvSeriesRepository{
        return TvSeriesRepositoryImpl(tvSeriesApi,pds)
    }

    @Singleton
    @Provides
    fun provideTvSeriesApi():TvSeriesApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvSeriesApi::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )


    //for firebase

    @Singleton
    @Provides
    fun providePostsDataSource(collectionPost: CollectionReference): PostDataSource{
        return PostDataSource(collectionPost)
    }

    @Singleton
    @Provides
    fun provideCollectionReference(): CollectionReference{
        return Firebase.firestore.collection("Posts")
    }


}