package com.account.manage.accountmanage.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class AccountBook(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Embedded
    var accountInfo: AccountInfo,

    @Enumerated(EnumType.STRING)
    val accountType: AccountType,

    @Enumerated(EnumType.STRING)
    val accountStatus: AccountStatus = AccountStatus.VALID,

    val deletedAt: LocalDateTime? = null
) : TimeEntity() {
}