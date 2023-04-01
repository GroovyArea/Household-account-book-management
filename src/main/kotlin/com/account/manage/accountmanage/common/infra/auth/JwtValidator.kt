package com.account.manage.accountmanage.common.infra.auth

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class JwtValidator(
    private val jwtYamlProperty: JwtYamlProperty,
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    fun validateAccessToken(token: String) =
        validateToken(token, TokenType.ACCESS)

    fun validateRefreshToken(token: String) =
        validateToken(token, TokenType.REFRESH)

    private fun validateToken(token: String, tokenType: TokenType): Boolean {
        val secretKey = if (tokenType == TokenType.ACCESS) jwtYamlProperty.accessSecretKey
        else jwtYamlProperty.refreshSecretKey

        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey.toByteArray())
                .build()
                .parseClaimsJws(token)

            return true
        } catch (ex: MalformedJwtException) {
            LOGGER.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            LOGGER.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            LOGGER.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            LOGGER.error("JWT claims string is empty.")
        }

        return false
    }

}

enum class TokenType {
    ACCESS,
    REFRESH,
}