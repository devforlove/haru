package com.senok.coreweb.exception

class ApiException(
    val errorCode: ErrorCode,
    override val message: String = errorCode.message
) : RuntimeException(message) {

    val status: Int
        get() = errorCode.status

    // 커스텀 메시지 생성자
    constructor(errorCode: ErrorCode, customMessage: String, cause: Throwable? = null) :
            this(errorCode, customMessage) {
        initCause(cause)
    }
}