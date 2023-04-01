package com.account.manage.accountmanage.accountbook.model

import com.account.manage.accountmanage.accountbook.domain.AccountBook
import com.account.manage.accountmanage.accountbook.domain.AccountType
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

sealed interface AccountBookResponse

data class AccountAddCompletionDto(
    val accountBookId: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val createdTime: LocalDateTime,
) : AccountBookResponse

data class AccountUpdateCompletionDto(
    val accountBookId: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedTime: LocalDateTime,
) : AccountBookResponse

data class AccountDeleteCompletionDto(
    val accountBookId: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val deletedTime: LocalDateTime,
) : AccountBookResponse

data class AccountRecoverCompletionDto(
    val accountBookId: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedTime: LocalDateTime,
) : AccountBookResponse

data class AccountDetailInfoDto(
    val ownerEmail: String,
    val memo: String = "",
    val amount: Int = 0,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedTime: LocalDateTime,
) : AccountBookResponse

data class AccountBooksInfoDto(
    val accountBookId: Long,
    val ownerEmail: String,
    val accountType : AccountType,
) {
    companion object {
        fun AccountBook.toAccountBooksInfoDto() : AccountBooksInfoDto {
            return AccountBooksInfoDto(
                accountBookId =  id,
                ownerEmail = user.email,
                accountType = accountInfo.accountType,
            )
        }
    }
}

data class AccountBooksWrapperDto(
    val accountBooksInfo : List<AccountBooksInfoDto>
) : AccountBookResponse