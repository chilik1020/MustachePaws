package com.chilik1020.mustachepaws.models.remote

import com.chilik1020.mustachepaws.utils.BASE_URL
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
class RetrofitClient {

    private val okHttpClient = createOkHttpClient()
    val serviceApi: MustachePawsApi = retrofit().create(MustachePawsApi::class.java)

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