package com.senok.coreweb.response

import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode

sealed class ResponseResult<out T> {

    /**
     * 성공 응답을 나타내는 data 클래스
     */
    data class Success<T>(val data: T) : ResponseResult<T>()

    /**
     * HTTp 4XX, 5XX 오류 응답을 나타내는 데이터 클래스
     */
    data class Failure(val errorResponse: ErrorResponse) : ResponseResult<Nothing>()

    inline fun onSuccess(action: (T) -> Unit): ResponseResult<T> {
        if (this is Success) {
            action(data)
        }
        return this
    }

    inline fun onFailure(action: (ErrorResponse) -> Unit): ResponseResult<T> {
        if (this is Failure) {
            action(errorResponse)
        }
        return this
    }

    fun getOrNull(): T? {
        if (this is Success) {
            return data
        }

        return null
    }

    fun getOrThrow(): T {
        return when (this) {
            is Success -> data
            is Failure -> {
                when {
                    errorResponse.isClientError -> throw ApiException(errorResponse, ErrorCode.CLIENT_ERROR)
                    else -> throw ApiException(errorResponse, ErrorCode.SERVER_ERROR)
                }
            }
        }
    }

    inline fun <R> getOrDefault(default: R, transform: (T) -> R): R {
        return when (this) {
            is Success -> transform(data)
            is Failure -> default
        }
    }

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure
}