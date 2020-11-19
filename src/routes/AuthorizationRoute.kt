package com.studentis.routes

import com.studentis.di.components.DaggerAuthencticationComponent
import com.studentis.di.components.DaggerRepoComponent
import com.studentis.models.Response
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

data class UserCredentials(
        val email: String,
        val password: String
)


fun Routing.authorization() {

    val authComponent = DaggerAuthencticationComponent
            .builder()
            .build()

    val repoComponent = DaggerRepoComponent
            .builder()
            .build()


    val hashService = authComponent.getHashService()
    val jwtService = authComponent.getJwtService()
    val userRepo = repoComponent.getUserRepo()

    route("/studentis/login") {
        post {

            val userCredentials = call.receive<UserCredentials>()

            val passwordHash = hashService.hashPassword (
                    userCredentials.password
            )

            val user = userRepo.getByPasswordHashEmailOrNull(passwordHash, userCredentials.email)

            if(user == null) {
                call.respond(
                        HttpStatusCode.Unauthorized,
                        Response("Incorrect login or password")
                )
            }
            else {
                call.respond(
                        HttpStatusCode.OK,
                        Response(jwtService.sign(user))
                )
            }
        }
    }
}