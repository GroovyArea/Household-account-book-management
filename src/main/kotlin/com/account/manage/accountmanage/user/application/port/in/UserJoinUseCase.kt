package com.account.manage.accountmanage.user.application.port.`in`

import com.account.manage.accountmanage.user.model.UserRequest
import com.account.manage.accountmanage.user.model.UserResponse

interface UserJoinUseCase {

    fun signUpUser(userRequest: UserRequest) : UserResponse

}