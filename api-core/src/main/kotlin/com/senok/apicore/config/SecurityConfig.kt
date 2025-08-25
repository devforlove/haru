package com.senok.apicore.config

import com.senok.apicore.config.auth.CustomOAuth2UserService
import com.senok.apicore.config.auth.OAuth2SuccessHandler
import com.senok.apicore.config.auth.TokenAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfig(
    val oAuth2UserService: CustomOAuth2UserService,
    val oAuth2SuccessHandler: OAuth2SuccessHandler
) {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer =
        WebSecurityCustomizer { web -> web.ignoring()
            .requestMatchers("/error", "favicon.ico")}

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        tokenAuthenticationFilter: TokenAuthenticationFilter,
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
                    .successHandler(oAuth2SuccessHandler)
            }
            // 인가 기능
            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}