package com.chilik1020.mustachepaws.models.repository

import com.chilik1020.mustachepaws.models.data.UserVO
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.models.remote.MustachePawsApi
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val service: MustachePawsApi, val preferences: AppPreferences) : UserRepository {

    override fun echoDetails(): Observable<UserVO> {
        return service.echoDetails(preferences.accessToken as String)
    }
}