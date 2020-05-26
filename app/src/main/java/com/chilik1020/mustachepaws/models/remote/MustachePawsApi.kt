package com.chilik1020.mustachepaws.models.remote

import com.chilik1020.mustachepaws.models.data.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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

    @POST("mustachepaws/posts/add")
    fun createPost(@Body post: PostRequestObject, @Header("Authorization")authorization: String): Observable<PostVO>

    @Multipart
    @POST("mustachepaws/posts/create")
    fun createPostWithPhoto(@Part("description") description: RequestBody,
                            @Part("creatorUsername") creatorUsername: RequestBody,
                            @Part file: MultipartBody.Part,
                            @Header("Authorization")authorization: String): Observable<PostVO>

}