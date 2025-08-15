package com.senok.user.application.out

import com.senok.user.application.out.dto.RegisterInfoDto

interface UpdateUserPort {
    fun updateRegisterInfo(dto: RegisterInfoDto)
}