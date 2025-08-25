package com.senok.user.application.out

import com.senok.coreeventpublisher.user.UserEvent

interface SaveUserRegisterEventPort {
    fun saveUserRegisterEvent(event: UserEvent)
}