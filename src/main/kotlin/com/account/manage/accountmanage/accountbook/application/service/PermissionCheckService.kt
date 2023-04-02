package com.account.manage.accountmanage.accountbook.application.service

import com.account.manage.accountmanage.accountbook.adapter.out.error.AccountBookErrorType
import com.account.manage.accountmanage.accountbook.domain.AccountBook
import com.account.manage.accountmanage.common.adpater.out.error.InvalidRequestException
import com.account.manage.accountmanage.user.domain.User
import org.springframework.stereotype.Service

@Service
class PermissionCheckService {

    fun checkAccountBookPermission(user: User, accountBook: AccountBook) {
        if (user != accountBook.user) {
            throw InvalidRequestException(AccountBookErrorType.NOT_OWNER_OF_ACCOUNT_BOOK)
        }
    }

}