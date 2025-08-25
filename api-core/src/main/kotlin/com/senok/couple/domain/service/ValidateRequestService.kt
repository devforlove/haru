package com.senok.couple.domain.service

import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import com.senok.couple.domain.model.Individual
import org.springframework.stereotype.Service

@Service
class ValidateRequestService {

    fun validateRequest(requestIndividual: Individual, requestedIndividual: Individual) {
        if (requestIndividual.gender == requestedIndividual.gender)
            throw ApiException(ErrorCode.CLIENT_ERROR, "Request with same gender not allowed")
    }
}