package com.account.manage.accountmanage.accountbook.domain

import com.account.manage.accountmanage.accountbook.domain.vo.AccountInfo
import com.account.manage.accountmanage.common.domain.TimeEntity
import com.account.manage.accountmanage.user.domain.User
import java.time.LocalDateTime
import java.time.ZoneId
import javax.persistence.*

@Entity
class AccountBook(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val user: User,

    @Embedded
    var accountInfo: AccountInfo,

    @Enumerated(EnumType.STRING)
    var accountStatus: AccountStatus = AccountStatus.VALID,

    var deletedAt: LocalDateTime? = null
) : TimeEntity() {


    companion object {
        fun createAccount(accountInfo: AccountInfo, user: User): AccountBook {
            return AccountBook(
                user = user,
                accountInfo = accountInfo,
            )
        }
    }

    fun updateAccountBookInfo(newAccountInfo: AccountInfo) {
        this.accountInfo = newAccountInfo
    }

    fun deleteAccountInfo() {
        this.deletedAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
        this.accountStatus = AccountStatus.REMOVED
    }

    fun recoverAccountInfo() {
        this.accountStatus = AccountStatus.VALID
    }
}