package com.studentis.authentication

import com.auth0.jwt.algorithms.Algorithm
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject

class HashService @Inject constructor() {

    companion object {
        private val hashSalt = "ii3hfbFfgAgZ@3g3".toByteArray()
    }

    fun hashPassword(password: String): String {

        val keySpec = PBEKeySpec(
                password.toCharArray(),
                hashSalt,
                 65536,
                128
        )

        val factory = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA1")

        val hash =  factory.generateSecret(keySpec).encoded

        return Base64.getEncoder().encodeToString(hash)
    }

}