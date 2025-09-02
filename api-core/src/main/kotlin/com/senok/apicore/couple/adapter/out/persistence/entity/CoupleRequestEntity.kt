package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.apicore.common.event.Events
import com.senok.corecommon.type.couple.CoupleRequestType
import com.senok.coreeventpublisher.event.couple.CoupleRequestEvent
import com.senok.coreeventpublisher.event.couple.CoupleRequestEventType
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "couple_request")
@Entity
class CoupleRequestEntity(

    @Column(name = "couple_id")
    val coupleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type")
    var coupleRequestType: CoupleRequestType,
): BaseEntity()