package com.senok.coreweb.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    CLIENT_ERROR(400, "client error"),
    NOT_FOUND(404, "resource not found"),
    ENTITY_NOT_FOUND(4041, "entity not found"),
    SERVER_ERROR(500, "internal server error"),
}