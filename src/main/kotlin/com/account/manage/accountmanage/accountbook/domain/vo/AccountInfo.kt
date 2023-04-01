package com.account.manage.accountmanage.accountbook.domain.vo

import com.account.manage.accountmanage.accountbook.domain.AccountType
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class AccountInfo(

    var memo: String = "",
    var amount: Int = 0,

    @Enumerated(EnumType.STRING)
    val accountType: AccountType,
)