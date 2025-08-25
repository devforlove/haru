package com.senok.apicore.common.exception

import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun handleThrowable(ex: ApiException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(ex.status)
            .body(ErrorResponse(ex.message, ex.errorCode.toString(), ex.status))
    }
}