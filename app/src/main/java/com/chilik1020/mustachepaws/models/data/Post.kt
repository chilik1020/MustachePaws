package com.chilik1020.mustachepaws.models.data

data class Post(
    val id: Long,
    val closed: Boolean,
    val description: String,
    val imageLink: String,
    val creatorUsername: String,
    val createdAt: String
)