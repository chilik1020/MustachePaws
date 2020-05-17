package com.chilik1020.mustachepaws.models.data

data class UserRequestObject(
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phonenumber: String,
    val password: String
)