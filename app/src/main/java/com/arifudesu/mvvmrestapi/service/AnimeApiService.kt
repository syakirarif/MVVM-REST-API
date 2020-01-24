package com.arifudesu.mvvmrestapi.service

import com.arifudesu.mvvmrestapi.BuildConfig
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AnimeApiService {

    @GET("season/{season_year}/{season_name}/")
    fun getAnimeSeason(
        @Path(value = "season_year", encoded = true) seasonYear: String,
        @Path(value = "season_name", encoded = true) seasonName: String
    ): Observable<AnimeApiModel<List<AnimeEntry>>>

    companion object {
        //        fun create(): SiakadApiService {
        val getAnimeApiService: AnimeApiService by lazy {
            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val mClient = OkHttpClient.Builder()
                .addInterceptor(mLoggingInterceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

            val mRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build()

//            return mRetrofit.create(SiakadApiService::class.java)
            mRetrofit.create(AnimeApiService::class.java)
        }
    }
}