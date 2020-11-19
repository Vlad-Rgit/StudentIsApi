package com.studentis.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.studentis.models.User
import io.ktor.auth.jwt.*
import io.ktor.server.engine.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JwtService @Inject constructor(){

    companion object {
        private val algorithm = Algorithm.HMAC256("74bb@uufgeug3h@FHr10")
        private const val issuer = "http://45.141.101.98"
        private const val audience = "student_is"
        private const val validityInMs = 36_000_00 * 24 // 24 hours
    }


    private fun getExpiredAt() =
            Date(System.currentTimeMillis() + validityInMs)


    val verifier = JWT
            .require(algorithm)
            .withIssuer(issuer)
            .withAudience(audience)
            .build()


    fun validate(credentials: JWTCredential, roleId: Int): JWTPrincipal? {

        val roleClaim = credentials.payload.getClaim("roleId")

        return if(roleClaim.isNull || roleClaim.asInt() != roleId)
            null
        else
            JWTPrincipal(credentials.payload)
    }


    fun sign(user: User): String = JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("userId", user.userId!!)
            .withClaim("roleId", user.roleId)
            .withExpiresAt(getExpiredAt())
            .sign(algorithm)

}