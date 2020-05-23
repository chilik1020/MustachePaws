package com.chilik1020.mustachepaws.models.repository

import com.chilik1020.mustachepaws.models.data.PostListVO
import com.chilik1020.mustachepaws.models.data.PostRequestObject
import com.chilik1020.mustachepaws.models.data.PostVO
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.models.remote.RetrofitClient
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(val client: RetrofitClient, val preferences: AppPreferences) : PostRepository {

    override fun fetchPosts() : Observable<PostListVO> {
       return client.serviceApi.fetchPosts(preferences.accessToken as String)
    }

    override fun createPost(post: PostRequestObject): Observable<PostVO> {
        return client.serviceApi.createPost(post, preferences.accessToken as String)
    }

    override fun createPostWithImage(
        description: RequestBody,
        creatorUsername: RequestBody,
        file: MultipartBody.Part
    ): Observable<PostVO> {
        return client.serviceApi.createPostWithPhoto(description, creatorUsername, file, preferences.accessToken as  String)
    }
}