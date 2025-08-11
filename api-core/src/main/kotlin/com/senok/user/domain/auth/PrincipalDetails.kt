package com.senok.user.domain.auth

import com.senok.user.adapter.out.persistence.entity.RoleType
import com.senok.user.adapter.out.persistence.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

data class PrincipalDetails(
    private val _username: String,
    private val userId: Long,
    private val roles: Set<RoleType>,
    private val userAttributes: Map<String, Any> = emptyMap(),
): OAuth2User, UserDetails {

    constructor(userEntity: UserEntity,
                roles: Set<RoleType>,
                userAttributes: Map<String, Any>,
    ): this(userEntity.email, userEntity.id.value!!, roles, userAttributes)

    val id = userId

    override fun getName(): String? {
        return null
    }

    override fun getAttributes(): Map<String, Any> {
        return userAttributes
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
       return roles.map { SimpleGrantedAuthority(it.toString()) }.toMutableSet()
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return _username
    }
}