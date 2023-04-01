package com.account.manage.accountmanage.common.adpater.out.error

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(

    @JsonProperty("error_code")
    val errorCode: Int,

    @JsonProperty("error_message")
    val errorMessage: String = "",

    @JsonProperty("error_details")
    val errorDetails: String = "",
) {
}