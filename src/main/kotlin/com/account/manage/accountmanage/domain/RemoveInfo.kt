package com.account.manage.accountmanage.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class RemoveInfo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_book_id")
    val accountBook: AccountBook,

    val deletedAt: LocalDateTime,
) : TimeEntity()