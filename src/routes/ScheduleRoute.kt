package com.studentis.routes

import com.studentis.di.components.DaggerRepoComponent
import com.studentis.models.Response
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.IllegalArgumentException

fun Routing.schedule() {

    val component = DaggerRepoComponent
            .builder()
            .build()

    val scheduleViewRepo = component.getScheduleViewRepo()

    route("schedule") {
        get {
            call.respond(scheduleViewRepo.getAll())
        }

        get("/{groupName}") {

            val groupName = call.parameters["groupName"]
                    ?: throw IllegalArgumentException("groupName parameter is missing")

            val schedule = scheduleViewRepo.getByGroupNameOrNull(groupName)

            if (schedule == null) {
                call.respond(
                        HttpStatusCode.NotFound,
                        Response("No schedule for this group")
                )
            }
            else {
                call.respond(
                        HttpStatusCode.OK,
                        schedule
                )
            }
        }
    }

}