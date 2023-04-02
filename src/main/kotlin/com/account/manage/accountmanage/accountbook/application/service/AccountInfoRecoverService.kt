package com.account.manage.accountmanage.accountbook.application.service

import com.account.manage.accountmanage.accountbook.adapter.out.error.AccountBookErrorType
import com.account.manage.accountmanage.accountbook.adapter.out.persistence.AccountBookRepository
import com.account.manage.accountmanage.accountbook.application.port.`in`.AccountInfoRecoverUseCase
import com.account.manage.accountmanage.accountbook.model.AccountBookRequest
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import com.account.manage.accountmanage.accountbook.model.AccountRecoverCompletionDto
import com.account.manage.accountmanage.accountbook.model.AccountRecoverDto
import com.account.manage.accountmanage.common.adpater.out.error.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountInfoRecoverService(
    private val accountBookRepository: AccountBookRepository,
    private val permissionCheckService: PermissionCheckService,
) : AccountInfoRecoverUseCase {

    @Transactional
    override fun accountInfoRecover(accountBookRequest: AccountBookRequest): AccountBookResponse {
        val accountRecoverDto = accountBookRequest as AccountRecoverDto
        val accountBookId = accountRecoverDto.accountBookId
        val user = accountRecoverDto.user

        val accountBook = (accountBookRepository.findAccountBookById(accountBookId)
            ?: throw DataNotFoundException(AccountBookErrorType.NOT_FOUND_ACCOUNT_BOOK))

        permissionCheckService.checkAccountBookPermission(user, accountBook)

        accountBook.recoverAccountInfo()

        return AccountRecoverCompletionDto(
            accountBookId = accountBookId,
            updatedTime = accountBook.updatedAt
        )
    }
}