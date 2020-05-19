package com.chilik1020.mustachepaws.models.remote

import com.chilik1020.mustachepaws.models.data.LoginRequestObject
import com.chilik1020.mustachepaws.models.data.PostListVO
import com.chilik1020.mustachepaws.models.data.UserRequestObject
import com.chilik1020.mustachepaws.models.data.UserVO
import com.chilik1020.mustachepaws.utils.BASE_URL
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface MustachePawsApi {

    @POST("mustachepaws/login")
    @Headers("Content-Type: application/json")
    fun login(@Body user: LoginRequestObject): Observable<retrofit2.Response<ResponseBody>>

    @POST("mustachepaws/users/registration")
    fun createUser(@Body user: UserRequestObject): Observable<UserVO>

    @GET("mustachepaws/users/details")
    fun echoDetails(@Header("Authorization") authorization: String) : Observable<UserVO>

    @GET("mustachepaws/posts/all")
    fun fetchPosts(@Header("Authorization") authorization: String): Observable<PostListVO>

    companion object Factory {
        private var service: MustachePawsApi? = null
        private val okHttpClient = createOkHttpClient()

        private fun createOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient().newBuilder()
            client.addInterceptor(loggingInterceptor)
            return client.build()
        }

        fun getInstance(): MustachePawsApi {
            if (service == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(okHttpClient)
                    .build()

            }
            return service as MustachePawsApi
        }
    }
}