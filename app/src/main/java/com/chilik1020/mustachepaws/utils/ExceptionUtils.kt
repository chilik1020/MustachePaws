package com.chilik1020.mustachepaws.utils

import android.util.Log
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

fun getMessageFromThrowable(t: Throwable): String {
    val message: String? = when(t) {
        is ConnectException -> {
            Log.d(LOG_TAG, "EXCEPTION: type = ConnectException.")
            "Нет соединения"
        }

        is SocketTimeoutException -> {
            Log.d(LOG_TAG, "EXCEPTION: type = SocketTimeoutException.")
            "Превышено время ожидания"
        }

        is HttpException -> {
            Log.d(LOG_TAG, "EXCEPTION: type = HttpException.")
            t.response().errorBody()?.string()
        }

        is TypeCastException -> {
            Log.d(LOG_TAG, "EXCEPTION: type = TypeCastException.")
            "Ошибка авторизации"
        }

        else -> {
            Log.d(LOG_TAG, "EXCEPTION: type = UnknownException.")
            "Неизвестная ошибка"
        }
    }
    Log.d(LOG_TAG, "MESSAGE: $message.")
    return message!!
}