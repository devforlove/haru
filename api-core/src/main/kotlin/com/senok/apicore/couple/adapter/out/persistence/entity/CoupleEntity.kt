package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.apicore.common.event.Events
import com.senok.corecommon.constants.CoupleConstant
import com.senok.corecommon.type.couple.CoupleStatus
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import com.senok.coreeventpublisher.event.couple.CoupleEventType
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "couple")
@Entity
class CoupleEntity(

    @Column(name = "female_id")
    val femaleId: Long,

    @Column(name = "male_id")
    val maleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "couple_status")
    var coupleStatus: CoupleStatus,

    @Column(name = "message_count")
    var messageCount: Int,
): BaseEntity() {

    @PostPersist
    private fun onPostCreate() {
        Events.raise(
            CoupleEvent(
                coupleId = id!!,
                eventType = CoupleEventType.REQUESTING,
                attributes = CoupleEvent.CoupleEventAttribute(LocalDateTime.now().plusDays(CoupleConstant.expiredDay)),
                createdAt = LocalDateTime.now()
            )
        )
    }
}
