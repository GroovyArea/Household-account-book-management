package com.account.manage.accountmanage.accountbook.application.service

import com.account.manage.accountmanage.accountbook.adapter.out.error.AccountBookErrorType
import com.account.manage.accountmanage.accountbook.adapter.out.persistence.AccountBookRepository
import com.account.manage.accountmanage.accountbook.application.port.`in`.AccountInfoUpdateUseCase
import com.account.manage.accountmanage.accountbook.model.*
import com.account.manage.accountmanage.common.adpater.out.error.DataNotFoundException
import com.account.manage.accountmanage.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class AccountInfoUpdateService(
    private val accountBookRepository: AccountBookRepository,
    private val permissionCheckService: PermissionCheckService,
) : AccountInfoUpdateUseCase {

    @Transactional
    override fun updateAccountInfo(
        accountBookRequest: AccountBookRequest, accountBookId: Long, user: User
    ): AccountBookResponse {
        val accountBook = accountBookRepository.findAccountBookById(accountBookId)
            ?: throw DataNotFoundException(AccountBookErrorType.NOT_FOUND_ACCOUNT_BOOK)

        permissionCheckService.checkAccountBookPermission(user, accountBook)

        val accountUpdateDto = accountBookRequest as AccountUpdateDto

        accountBook.updateAccountBookInfo(accountUpdateDto.toVo(accountUpdateDto))

        return AccountUpdateCompletionDto(
            accountBookId = accountBookId,
            updatedTime = accountBook.updatedAt
        )
    }

    @Transactional
    override fun deleteAccount(
        accountBookId: Long, user: User
    ): AccountBookResponse {
        val accountBook = accountBookRepository.findAccountBookById(accountBookId)
            ?: throw DataNotFoundException(AccountBookErrorType.NOT_FOUND_ACCOUNT_BOOK)

        permissionCheckService.checkAccountBookPermission(user, accountBook)

        accountBook.deleteAccountInfo()

        return AccountDeleteCompletionDto(
            accountBookId = accountBook.id,
            deletedTime = accountBook.deletedAt ?: LocalDateTime.now(ZoneId.of("Asia/Seoul")),
        )
    }
}