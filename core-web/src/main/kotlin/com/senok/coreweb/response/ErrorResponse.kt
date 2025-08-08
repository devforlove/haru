package com.senok.coreweb.response

data class ErrorResponse(
    val message: String,
    val code: String,
    val status: Int
) {
    val isClientError: Boolean
        get() = status in 400..499
}