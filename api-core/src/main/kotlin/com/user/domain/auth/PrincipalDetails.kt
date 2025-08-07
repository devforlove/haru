package com.senok.user.domain.auth

import com.senok.user.adapter.out.persistence.entity.User
import com.senok.user.adapter.out.persistence.entity.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

data class PrincipalDetails(
    val user: User,
    val roles: Set<UserRole>,
    val userAttributes: Map<String, Any>,
    val attributeKey: String
): OAuth2User, UserDetails {

    override fun getName(): String {
        return attributes[attributeKey].toString()
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
        return user.email
    }
}