package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.corecommon.types.couple.CoupleRequestType
import jakarta.persistence.*

@Table(name = "couple_request")
@Entity
class CoupleRequestEntity(

    @Column(name = "couple_id")
    val coupleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type")
    var coupleRequestType: CoupleRequestType,
): BaseEntity()