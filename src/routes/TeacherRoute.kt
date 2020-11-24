package com.studentis.routes

import com.studentis.di.components.DaggerAuthencticationComponent
import com.studentis.di.components.DaggerRepoComponent
import com.studentis.models.Response
import com.studentis.models.Teacher
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.launch

fun Routing.teachers() {

    val repoComponent = DaggerRepoComponent
            .builder()
            .build()

    val authComponent = DaggerAuthencticationComponent
            .builder()
            .build()

    val teacherRepo = repoComponent.getTeacherRepo()
    val hashService = authComponent.getHashService()


    route("/studentis/teacher") {
        post {
            val teacher = call.receive<Teacher>()
            teacher.passwordHash = hashService.hashPassword(teacher.passwordHash)
            teacherRepo.add(teacher)
            call.respond(
                    HttpStatusCode.OK,
                    Response("Teacher added")
            )
        }
    }

}