package com.account.manage.accountmanage.common.infra.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.lang.Strings
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtProvider(
    private val jwtYamlProperty: JwtYamlProperty,
) {

    companion object {
        const val TOKEN_PREFIX = "Bearer "
        const val AUTH_HEADER = "Authorization"
    }

    fun generateRefreshToken(userId: Long): String {
        return generateToken(userId, jwtYamlProperty.refreshExpiration, jwtYamlProperty.refreshSecretKey)
    }

    fun generateAccessToken(userId: Long): String {
        return generateToken(userId, jwtYamlProperty.accessExpiration, jwtYamlProperty.accessSecretKey)
    }

    fun getUserIdFromToken(token: String): Long {
        return Jwts.parserBuilder()
            .setSigningKey(jwtYamlProperty.accessSecretKey.toByteArray())
            .build()
            .parseClaimsJwt(token)
            .body["id"] as Long
    }

    private fun generateToken(userId: Long, expiryMs: Long, secretKey: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expiryMs)

        return Jwts.builder()
            .claim("id", userId)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(getSignedKey(secretKey), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getSignedKey(secretKey: String): Key {
        return Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

}