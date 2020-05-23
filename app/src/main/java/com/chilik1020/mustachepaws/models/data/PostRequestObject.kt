package com.chilik1020.mustachepaws.models.data

data class PostRequestObject(
    val closed: Boolean,
    val description: String,
    val imageLink: String,
    val creatorUsername: String
)