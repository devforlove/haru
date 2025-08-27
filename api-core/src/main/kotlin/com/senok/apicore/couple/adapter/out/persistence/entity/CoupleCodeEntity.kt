package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.apicore.common.event.Events
import com.senok.coreeventpublisher.couple.CoupleCodeEvent
import com.senok.coreeventpublisher.couple.CoupleCodeEventType
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "couple_code")
@Entity
class CoupleCodeEntity(

    @Column(name = "couple_id")
    val coupleId: Long,

    @Column(name = "code")
    val code: String,

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime,
) : BaseEntity() {

    @PostPersist
    private fun onPostCreate() {
        Events.raise(
            CoupleCodeEvent(
                coupleId,
                CoupleCodeEventType.REGISTER,
                CoupleCodeEvent.CoupleCodeEventAttribute(expiredAt),
                LocalDateTime.now()
            )
        )
    }
}
