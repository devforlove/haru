package com.senok.notification.dao.repository

import com.senok.corecommon.types.couple.CoupleStatus
import com.senok.notification.dao.entity.Couple
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

interface CoupleRepository: Repository<Couple, Long> {
    fun saveCouple(couple: Couple)
    
    @Modifying
    @Query("UPDATE FROM Couple c SET c.coupleStatus = :status WHERE c.id = :coupleId")
    fun updateCoupleStatus(@Param(value = "coupleId") coupleId: Long, @Param(value = "status")  status: CoupleStatus)
}