package com.account.manage.accountmanage.accountbook.model

import com.account.manage.accountmanage.accountbook.domain.AccountType
import com.account.manage.accountmanage.accountbook.domain.vo.AccountInfo
import com.account.manage.accountmanage.user.domain.User
import org.springframework.data.domain.Pageable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

sealed interface AccountBookRequest

data class AccountAddDto(
    @NotNull
    val amount: Int,

    @NotBlank
    val memo: String,

    @NotNull
    val accountType: AccountType,
) : AccountBookRequest {

    fun toVo(accountAddDto: AccountAddDto): AccountInfo {
        return AccountInfo(
            amount = accountAddDto.amount,
            memo = accountAddDto.memo,
            accountType = accountAddDto.accountType,
        )
    }
}

data class AccountUpdateDto(
    @NotNull
    val amount: Int,

    @NotBlank
    val memo: String,

    @NotNull
    val accountType: AccountType,
) : AccountBookRequest {

    fun toVo(accountUpdateDto: AccountUpdateDto): AccountInfo {
        return AccountInfo(
            amount = accountUpdateDto.amount,
            memo = accountUpdateDto.memo,
            accountType = accountUpdateDto.accountType,
        )
    }
}

data class AccountBooksDto(
    val user: User,
    val pageable: Pageable,
) : AccountBookRequest