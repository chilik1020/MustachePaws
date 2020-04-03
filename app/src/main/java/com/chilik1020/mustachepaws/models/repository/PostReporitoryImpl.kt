package com.chilik1020.mustachepaws.models.repository

import com.chilik1020.mustachepaws.models.data.Post

class PostReporitoryImpl {


    fun fetchPosts() : List<Post> {
        val posts = mutableListOf<Post>()
        for (i in 0..10) {
            posts.add(
                Post(
                i.toLong(),
                false,
                "Пропала собака в нашем дворе: $i",
                "link to image",
                "chilik1020",
                "2/04/2020"
            )
            )
        }
        return posts
    }
}