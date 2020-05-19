package com.chilik1020.mustachepaws.models.repository

import com.chilik1020.mustachepaws.models.data.UserVO
import io.reactivex.Observable

interface UserRepository {

    fun echoDetails(): Observable<UserVO>
}