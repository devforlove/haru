package com.senok.apicore.user.application

import com.senok.apicore.common.event.Events
import com.senok.apicore.user.adapter.out.persistence.DeviceEventPersistenceAdapter
import com.senok.coreeventpublisher.event.device.DeviceEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class DeviceEventHandler(
    private val deviceEventPersistenceAdapter: DeviceEventPersistenceAdapter
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleBeforeTransaction(event: DeviceEvent) {
        deviceEventPersistenceAdapter.saveDeviceEvent(event)
        Events.raise(event)
    }
}