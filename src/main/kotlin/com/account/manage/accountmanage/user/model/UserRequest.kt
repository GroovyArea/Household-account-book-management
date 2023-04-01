package com.account.manage.accountmanage.user.model

import com.account.manage.accountmanage.user.domain.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank


sealed interface UserRequest

data class UserJoinDto(
    @Email
    @NotBlank
    val email: String,

    @NotBlank
    val password: String,
) : UserRequest {

    fun toEntity(userJoinDto: UserJoinDto): User {
        return User(
            email = userJoinDto.email,
            password = userJoinDto.password,
        )
    }
}