package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.couple.adapter.out.persistence.mapper.CoupleRequestMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleRequestRepository
import com.senok.apicore.couple.application.out.FindCoupleRequestPort
import com.senok.apicore.couple.application.out.SaveCoupleRequestPort
import com.senok.apicore.couple.domain.model.CoupleRequest
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import org.springframework.stereotype.Component

@Component
class CoupleRequestPersistenceAdapter(
    private val coupleRequestRepository: CoupleRequestRepository,
    private val mapper: CoupleRequestMapper
): FindCoupleRequestPort, SaveCoupleRequestPort {

    override fun findCoupleRequest(requestId: Long): CoupleRequest {
        return coupleRequestRepository.findById(requestId)?.let { entity ->
            mapper.toDomain(entity)
        } ?: throw ApiException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun saveCoupleRequest(coupleRequest: CoupleRequest) {
        mapper.toEntity(coupleRequest).let { entity ->
            coupleRequestRepository.save(entity) }
    }
}