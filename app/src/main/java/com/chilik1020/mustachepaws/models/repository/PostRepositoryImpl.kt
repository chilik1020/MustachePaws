package com.chilik1020.mustachepaws.models.repository

import com.chilik1020.mustachepaws.models.data.PostListVO
import com.chilik1020.mustachepaws.models.data.PostRequestObject
import com.chilik1020.mustachepaws.models.data.PostVO
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.models.remote.MustachePawsApi
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(val service: MustachePawsApi, val preferences: AppPreferences) : PostRepository {

    override fun fetchPosts() : Observable<PostListVO> {
        return service.fetchPosts(preferences.accessToken as String)
    }

    override fun createPost(post: PostRequestObject): Observable<PostVO> {
        return service.createPost(post, preferences.accessToken as String)
    }

    override fun createPostWithImage(
        description: RequestBody,
        creatorUsername: RequestBody,
        file: MultipartBody.Part
    ): Observable<PostVO> {
        return service.createPostWithPhoto(description, creatorUsername, file, preferences.accessToken as  String)
    }
}