package com.senok.apicore.user.application.out

import com.senok.coreeventpublisher.user.UserEvent

interface SaveUserEventPort {
    fun saveUserEvent(event: UserEvent)
}