package com.studentis.routes

import com.studentis.di.components.DaggerRepoComponent
import io.ktor.application.*
import io.ktor.auth.*
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
    }
}