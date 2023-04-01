package com.account.manage.accountmanage.accountbook.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

sealed interface AccountBookResponse

data class AccountAddCompletionDto(
    val accountBookId: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val createdTime : LocalDateTime,
) : AccountBookResponse

data class AccountUpdateCompletionDto(
    val accountBookId: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedTime: LocalDateTime,
) : AccountBookResponse

data class AccountDeleteCompletionDto(
    val accountBookId: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val deletedTime: LocalDateTime,
) : AccountBookResponse

data class AccountRecoverCompletionDto(
    val accountBookId: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedTime: LocalDateTime,
) : AccountBookResponse
