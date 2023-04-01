package com.account.manage.accountmanage.user.domain

import com.account.manage.accountmanage.common.domain.TimeEntity
import javax.persistence.*

@Table(name = "users")
@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var passwordSalt: String = "",
) : TimeEntity() {

    fun updatePasswordInfo(securedPassword: String, newSalt: String) {
        this.password = securedPassword
        this.passwordSalt = newSalt
    }
}