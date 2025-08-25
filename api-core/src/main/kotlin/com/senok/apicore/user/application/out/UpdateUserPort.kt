package com.senok.apicore.user.application.out

import com.senok.apicore.user.application.out.dto.RegisterInfoDto

interface UpdateUserPort {
    fun updateRegisterInfo(dto: RegisterInfoDto)
}