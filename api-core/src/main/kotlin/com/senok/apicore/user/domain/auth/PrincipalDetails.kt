package com.senok.apicore.user.domain.auth

import com.senok.apicore.user.adapter.out.persistence.entity.UserEntity
import com.senok.apicore.user.domain.user.User
import com.senok.corecommon.types.user.RoleType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

data class PrincipalDetails(
    private val email: String,
    private val userId: Long,
    private val roles: Set<RoleType>,
    private val userAttributes: Map<String, Any> = emptyMap(),
): OAuth2User, UserDetails {

    constructor(user: User,
                roles: Set<RoleType>,
                userAttributes: Map<String, Any>,
    ): this(user.email, user.id, roles, userAttributes)

    val id = userId

    override fun getName(): String? {
        return null
    }

    override fun getAttributes(): Map<String, Any> {
        return userAttributes
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
       return roles.map { roleType -> SimpleGrantedAuthority(roleType.toString()) }.toMutableSet()
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return email
    }
}