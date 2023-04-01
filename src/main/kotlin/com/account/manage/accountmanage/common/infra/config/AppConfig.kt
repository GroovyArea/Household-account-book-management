package com.account.manage.accountmanage.common.infra.config

import com.account.manage.accountmanage.adapter.`in`.filter.AuthenticationFilter
import com.account.manage.accountmanage.adapter.`in`.filter.LoginFilter
import com.account.manage.accountmanage.common.adpater.out.error.ErrorHandlingFilter
import com.account.manage.accountmanage.user.adapter.out.persistence.UserRepository
import com.account.manage.accountmanage.common.infra.auth.JwtProvider
import com.account.manage.accountmanage.common.infra.auth.JwtValidator
import com.account.manage.accountmanage.common.infra.auth.JwtYamlProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(JwtYamlProperty::class)
class AppConfig(
    private val objectMapper: ObjectMapper,
    private val jwtProvider: JwtProvider,
    private val jwtValidator: JwtValidator,
    private val userRepository: UserRepository,
) {

    @Bean
    fun errorHandlingFilterRegistration(): FilterRegistrationBean<ErrorHandlingFilter> {
        val registrationBean = FilterRegistrationBean<ErrorHandlingFilter>()
        registrationBean.filter = ErrorHandlingFilter()
        registrationBean.addUrlPatterns("/api/v1/**")
        registrationBean.order = Integer.MAX_VALUE
        return registrationBean
    }

    @Bean
    fun loginFilterRegistration(): FilterRegistrationBean<LoginFilter> {
        val registrationBean = FilterRegistrationBean<LoginFilter>()
        registrationBean.filter = LoginFilter(userRepository, objectMapper)
        registrationBean.addUrlPatterns("/api/v1/sign-in")
        return registrationBean
    }

    @Bean
    fun authenticationFilterRegistration(): FilterRegistrationBean<AuthenticationFilter> {
        val registrationBean = FilterRegistrationBean<AuthenticationFilter>()
        registrationBean.filter = AuthenticationFilter(jwtValidator, jwtProvider, userRepository)
        registrationBean.addUrlPatterns("/api/v1/**")
        return registrationBean
    }

}