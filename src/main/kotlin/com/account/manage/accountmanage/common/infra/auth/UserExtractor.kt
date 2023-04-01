package com.account.manage.accountmanage.common.infra.auth

import com.account.manage.accountmanage.user.domain.User
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class UserExtractor {

    fun getUserFromRequest(request: HttpServletRequest) =
        request.getAttribute("user") as User

}