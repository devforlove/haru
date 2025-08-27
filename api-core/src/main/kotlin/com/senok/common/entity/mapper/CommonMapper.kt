package com.senok.common.entity.mapper

interface CommonMapper<E, D> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}