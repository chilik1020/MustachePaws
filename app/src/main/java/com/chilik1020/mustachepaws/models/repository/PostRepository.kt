package com.chilik1020.mustachepaws.models.repository

import com.chilik1020.mustachepaws.models.data.PostListVO
import com.chilik1020.mustachepaws.models.data.PostRequestObject
import com.chilik1020.mustachepaws.models.data.PostVO
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PostRepository {

    fun fetchPosts(): Observable<PostListVO>

    fun createPost(post: PostRequestObject) : Observable<PostVO>

    fun createPostWithImage(description: RequestBody,
                            creatorUsername: RequestBody,
                            file: MultipartBody.Part): Observable<PostVO>
}