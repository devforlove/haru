package com.senok.common.response

import org.springframework.http.ResponseEntity

inline fun <reified T> ResponseEntity<String>.responseResult(): ResponseResult<T> {
    return when (this.statusCode.is2xxSuccessful) {
        true -> ResponseResult.Success(defaultObjectMapper.readValue(this.body, T::class.java))
        else -> {
            val responseBody = this.body?.toString()!!
            ResponseResult.Failure(
                when {
                    isErrorResponseDeserializable(responseBody) -> defa
                }
            )
        }
    }
}

fun isErrorResponseDeserializable(responseBody: String): Boolean {
    return when (val rootNode = defaultObjectMapper.readTree(responseBody)) {
        null -> false
        else -> rootNode.path("message").isTextual && rootNode.path("status").isNumber && rootNode.path("code").isTextual
    }
}