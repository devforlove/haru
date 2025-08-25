package com.senok.apicore.common.entity.mapper

interface CommonMapper<E, D> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}