package com.account.manage.accountmanage.accountbook.adapter.out.persistence

import com.account.manage.accountmanage.accountbook.domain.AccountBook
import org.springframework.data.jpa.repository.JpaRepository

interface AccountBookRepository : JpaRepository<AccountBook, Long> {

    fun findAccountBookById(accountBookId: Long) : AccountBook?
}