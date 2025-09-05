package com.senok.notification.dao.entity

import jakarta.persistence.*

@Entity
class Couple(
    @AttributeOverrides(
        AttributeOverride(name = "user_id", column = Column(name = "female_id")),
        AttributeOverride(name = "nickname", column = Column(name = "female_nickname"))
    )
    val female: CoupleUser,
    
    @AttributeOverrides(
        AttributeOverride(name = "user_id", column = Column(name = "male_id")),
        AttributeOverride(name = "nickname", column = Column(name = "male_nickname"))
    )
    @Column(name = "male_id")
    val male: CoupleUser
): BaseEntity() {
    
    @Embeddable
    class CoupleUser(
        @Column(name = "user_id")
        val femaleId: Long,
        
        @Column(name = "nickname")
        val nickname: String,
    )
}