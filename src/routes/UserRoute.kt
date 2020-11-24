package com.studentis.routes

import com.studentis.di.components.DaggerRepoComponent
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.users() {

    val component = DaggerRepoComponent
        .builder()
        .build()

    val userRepo = component.getUserRepo()


    route("/studentis/users") {
        authenticate("admin") {
            get {
                call.respond(userRepo.getAll())
            }
        }

        authenticate("all") {
            get("/{userId}") {

                val jwtPrincipal = call.principal<JWTPrincipal>()!!

                val claimUserId = jwtPrincipal
                        .payload
                        .getClaim("userId")
                        .asInt()

                val claimRoleId = jwtPrincipal
                        .payload
                        .getClaim("userId")
                        .asInt()

                val userId = call.parameters["userId"]!!.toInt()

                if(userId != claimUserId &&
                        claimRoleId != 5) {
                    call.respond(HttpStatusCode.Unauthorized)
                }
                else {
                    call.respond(userRepo.getById(userId))
                }
            }
        }

    }
}