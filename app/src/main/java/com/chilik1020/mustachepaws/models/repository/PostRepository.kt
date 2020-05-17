package com.chilik1020.mustachepaws.models.repository

import com.chilik1020.mustachepaws.models.data.PostListVO
import io.reactivex.Observable

interface PostRepository {

    fun fetchPosts(): Observable<PostListVO>
}