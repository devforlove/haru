package com.senok.notification.dao.repository

import com.senok.notification.dao.entity.Device
import org.springframework.data.repository.Repository

interface DeviceRepository: Repository<Device, Long> {
    fun save(device: Device): Device
}