package com.chilik1020.mustachepaws.models.repository

import com.chilik1020.mustachepaws.models.data.PostListVO
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.models.remote.RetrofitClient
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(val client: RetrofitClient, val preferences: AppPreferences) : PostRepository {

    override fun fetchPosts() : Observable<PostListVO> {
       return client.serviceApi.fetchPosts(preferences.accessToken as String)
    }
}