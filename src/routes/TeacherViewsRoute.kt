package com.studentis.routes

import com.studentis.di.components.DaggerRepoComponent
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.teacherViews() {

    val component = DaggerRepoComponent
            .builder()
            .build()

    val teacherViewRepo = component.getTeacherViewRepo()

    route("/studentis/teacherViews") {
        get {
            call.respond(teacherViewRepo.getAll())
        }
    }
}