package com.account.manage.accountmanage.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.time.ZoneId
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class TimeEntity {

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))

    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
}