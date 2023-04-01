package com.account.manage.accountmanage.common.adpater.out.error

sealed class AccountManageException(val errorType: ErrorType) :
    RuntimeException(errorType.getErrorDetails())

class DataNotFoundException(errorType: ErrorType) :
    AccountManageException(errorType)

class InvalidRequestException(errorType: ErrorType) :
    AccountManageException(errorType)

class UnAuthorizedException(errorType: ErrorType) :
    AccountManageException(errorType)

class NotAllowedException(errorType: ErrorType) :
    AccountManageException(errorType)

class ConflictException(errorType: ErrorType) :
    AccountManageException(errorType)