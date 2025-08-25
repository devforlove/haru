package com.senok.apicore.user.application.out

import com.senok.coreeventpublisher.user.UserEvent

interface SaveUserRegisterEventPort {
    fun saveUserRegisterEvent(event: UserEvent)
}