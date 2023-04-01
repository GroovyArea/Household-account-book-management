package com.account.manage.accountmanage.accountbook.adapter.out.persistence

import com.account.manage.accountmanage.accountbook.domain.AccountBook
import com.account.manage.accountmanage.accountbook.domain.AccountStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AccountBookRepository : JpaRepository<AccountBook, Long> {

    fun findAccountBookById(accountBookId: Long): AccountBook?

    @Query(
        "select a " +
                " from AccountBook a " +
                " where a.user.id = :userId" +
                " and a.accountStatus = :accountStatus"
    )
    fun findAccountBooksByUserId(
        pageable: Pageable,
        userId: Long,
        accountStatus: AccountStatus = AccountStatus.VALID
    ): Page<AccountBook>
}