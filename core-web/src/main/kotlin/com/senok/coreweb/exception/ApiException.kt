package com.senok.coreweb.exception

import com.senok.coreweb.response.ErrorResponse

class ApiException(
    val errorResponse: ErrorResponse,
    val errorCode: ErrorCode,
): RuntimeException()