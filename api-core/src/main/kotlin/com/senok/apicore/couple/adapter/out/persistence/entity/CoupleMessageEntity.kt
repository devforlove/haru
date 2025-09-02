package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.corecommon.types.couple.CoupleMessageStatus
import com.senok.corecommon.types.couple.CoupleMessageType
import jakarta.persistence.*

@Table(name = "couple_history")
@Entity
class CoupleMessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long? = null,
    
    @Column(name = "couple_id")
    val coupleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    val type: CoupleMessageType,

    @Enumerated(EnumType.STRING)
    @Column(name = "message_status")
    var status: CoupleMessageStatus,
    
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "userId", column = Column(name = "female_user_id")),
        AttributeOverride(name = "text", column = Column(name = "female_user_text"))
    )
    val femaleMessageContent: MessageContentValue,
    
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "userId", column = Column(name = "male_user_id")),
        AttributeOverride(name = "text", column = Column(name = "male_user_text"))
    )
    val maleMessageContent: MessageContentValue,
): BaseEntity() {
    
    @Embeddable
    class MessageContentValue(
        val userId: Long,
        var text: String?
    ) {
        fun withText(text: String?) {
            this.text = text
        }
    }
}
