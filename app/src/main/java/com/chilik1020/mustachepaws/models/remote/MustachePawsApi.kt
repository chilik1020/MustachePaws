package com.chilik1020.mustachepaws.models.remote

import com.chilik1020.mustachepaws.models.data.LoginRequestObject
import com.chilik1020.mustachepaws.models.data.PostListVO
import com.chilik1020.mustachepaws.models.data.UserRequestObject
import com.chilik1020.mustachepaws.models.data.UserVO
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface MustachePawsApi {

    @POST("mustachepaws/login")
    @Headers("Content-Type: application/json")
    fun login(@Body user: LoginRequestObject): Observable<retrofit2.Response<ResponseBody>>

    @GET("mustachepaws/users/details")
    fun echoDetails(@Header("Authorization") authorization: String) : Observable<UserVO>

    @POST("mustachepaws/users/registration")
    fun createUser(@Body user: UserRequestObject): Observable<UserVO>

    @GET("mustachepaws/posts/all")
    fun fetchPosts(@Header("Authorization") authorization: String): Observable<PostListVO>


}