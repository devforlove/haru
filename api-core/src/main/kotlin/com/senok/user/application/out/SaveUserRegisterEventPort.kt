package com.senok.user.application.out

import com.senok.user.domain.user.UserRegisterEvent

interface SaveUserRegisterEventPort {
    fun saveUserRegisterEvent(event: UserRegisterEvent)
}