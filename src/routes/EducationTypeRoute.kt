package com.studentis.routes

import com.studentis.di.components.DaggerRepoComponent
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.educationType() {

    val component = DaggerRepoComponent
            .builder()
            .build()

    val educationTypeRepo = component.getEducationTypeRepo()


    route("/studentis/educationTypes") {
        get {
            call.respond(educationTypeRepo.getAll())
        }
    }

}