package com.account.manage.accountmanage.common.infra.auth

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.*


object PasswordEncrypt {

    fun getSalt(): String {
        val random: Random = SecureRandom()
        val salt = ByteArray(10)
        random.nextBytes(salt)
        val sb = StringBuilder()
        for (i in salt.indices) {
            sb.append(String.format("%02x", salt[i]))
        }
        return sb.toString()
    }

    @Throws(NoSuchAlgorithmException::class)
    fun getSecurePassword(pwd: String, salt: String): String {
        val bytesArrOfSalt = salt.toByteArray()
        val result: String
        val temp = pwd.toByteArray()
        val bytes = ByteArray(temp.size + bytesArrOfSalt.size)
        System.arraycopy(temp, 0, bytes, 0, temp.size)
        System.arraycopy(salt.toByteArray(), 0, bytes, temp.size, salt.toByteArray().size)
        val md: MessageDigest = MessageDigest.getInstance("SHA-256")
        md.update(bytes)
        val b: ByteArray = md.digest()
        val sb = StringBuilder()

        for (i in b.indices) {
            sb.append(
                ((b[i].toInt() and 0xFF) + 256)
                    .toString(16)
                    .substring(1)
            )
        }
        result = sb.toString()
        return result
    }

}