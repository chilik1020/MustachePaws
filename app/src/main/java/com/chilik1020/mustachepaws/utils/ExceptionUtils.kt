package com.chilik1020.mustachepaws.utils

import android.util.Log
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun getMessageFromThrowable(t: Throwable): String {
    val message: String? = when(t) {
        is SocketTimeoutException -> {
            Log.d(LOG_TAG, "EXCEPTION: type = SocketTimeoutException.")
            "Timeout"
        }
        is HttpException -> {
            Log.d(LOG_TAG, "EXCEPTION: type = HttpException.")
            t.response().errorBody()?.string()
        }
        else -> {
            Log.d(LOG_TAG, "EXCEPTION: type = UnknownException.")
            "UnknownError"
        }
    }
    Log.d(LOG_TAG, "MESSAGE: $message.")
    return message!!
}