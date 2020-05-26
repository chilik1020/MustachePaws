package com.chilik1020.mustachepaws.di

import com.chilik1020.mustachepaws.models.remote.MustachePawsApi
import com.chilik1020.mustachepaws.utils.BASE_URL
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import toothpick.config.Module

class NetworkModule : Module() {
    private val okHttpClient = createOkHttpClient()

    init {
        val serviceApi: MustachePawsApi = retrofit().create(MustachePawsApi::class.java)
        bind(MustachePawsApi::class.java).toInstance(serviceApi)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient().newBuilder()
        client.addInterceptor(loggingInterceptor)
        return client.build()
    }

    private fun retrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()
}