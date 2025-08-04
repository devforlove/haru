package com.senok.auth.config

import com.senok.auth.application.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfig(
    val oAuth2UserService: CustomOAuth2UserService
) {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer =
        WebSecurityCustomizer { web -> web.ignoring()
            .requestMatchers("/error", "favicon.ico")}

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
    ): SecurityFilterChain {

        return http
            .httpBasic { it.disable()}
            .formLogin { it.disable() }
            .cors { it.disable() }
            .csrf { it.disable() }
            .logout { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { request ->
                request.requestMatchers(
                    AntPathRequestMatcher("/")
                ).permitAll()
                .anyRequest().authenticated()
            }
            .oauth2Login { oauth2 ->
                oauth2
                    .userInfoEndpoint {
                        it.userService(oAuth2UserService)
                    }
                    .successHandler(null)
            }
            .build()
    }
}