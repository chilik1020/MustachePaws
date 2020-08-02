package com.chilik1020.mustachepaws.models.data

data class PostVO(
    val id: Long,
    val closed: Boolean,
    val typeOfAnimal: String,
    val ageOfAnimal: String,
    val typeOfHelp: String,
    val description: String,
    val imageLink: String,
    val creatorUsername: String,
    val createdAt: String
)

data class PostListVO (
    val posts: List<PostVO>
)