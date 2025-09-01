package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.apicore.common.event.Events
import com.senok.corecommon.type.couple.CoupleMessageStatus
import com.senok.corecommon.type.couple.CoupleMessageType
import jakarta.persistence.*

@Table(name = "couple_history")
@Entity
class CoupleMessageEntity(

    @Column(name = "couple_id")
    val coupleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    val type: CoupleMessageType,

    @Enumerated(EnumType.STRING)
    @Column(name = "message_status")
    var status: CoupleMessageStatus,

    @Column(name = "text_from_female")
    var textFromFemale: String?,

    @Column(name = "text_from_male")
    var textFromMale: String?,
): BaseEntity() {

    @PostUpdate
    private fun onPostUpdate() {
//        Events.raise(
//
//        )
    }
}
