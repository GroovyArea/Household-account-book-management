package com.account.manage.accountmanage.common.infra.auth

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.jwt")
class JwtYamlProperty(

    val accessSecretKey: String,
    val refreshSecretKey: String,
    val accessExpiration: Long,
    val refreshExpiration: Long
)