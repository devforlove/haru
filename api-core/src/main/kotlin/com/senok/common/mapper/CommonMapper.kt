package com.senok.common.mapper

interface CommonMapper<E, D> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}