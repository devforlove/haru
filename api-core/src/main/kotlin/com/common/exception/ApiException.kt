package com.senok.common.exception

import com.senok.common.response.ErrorResponse

class ApiException(
    val errorResponse: ErrorResponse,
    val errorCode: ErrorCode,
): RuntimeException()