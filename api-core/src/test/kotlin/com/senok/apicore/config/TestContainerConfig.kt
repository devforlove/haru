package com.senok.apicore.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfig {

    @Bean
    @ServiceConnection
    fun mysql(): MySQLContainer<*> =
        MySQLContainer(DockerImageName.parse("mysql:8.4.0"))
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
}