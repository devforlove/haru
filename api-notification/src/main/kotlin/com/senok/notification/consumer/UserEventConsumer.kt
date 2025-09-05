package com.senok.notification.consumer

import com.senok.coreeventpublisher.constants.ConsumerGroupConstants
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.user.UserEvent
import com.senok.coreeventpublisher.event.user.UserEventType
import com.senok.notification.service.UserService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class UserEventConsumer(
    private val userService: UserService,
) {
    
    @KafkaListener(
        topics = [TopicConstants.USER],
        groupId = ConsumerGroupConstants.USER_MESSAGE_CONSUMER_GROUP,
        containerFactory = "userEventContainerFactory",
    )
    fun consume(consumerRecord: ConsumerRecord<String, UserEvent>, ack: Acknowledgment) {
        consumerRecord.value()?.let {
            when (it.eventType) {
                UserEventType.REGISTER -> userService.saveUser(it)
            }
        }
        
        ack.acknowledge()
    }
}