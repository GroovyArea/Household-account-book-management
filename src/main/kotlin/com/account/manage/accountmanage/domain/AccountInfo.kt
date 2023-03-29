package com.account.manage.accountmanage.domain

import javax.persistence.Embeddable

@Embeddable
data class AccountInfo(

    var memo: String = "",
    var amount: Int = 0,
)