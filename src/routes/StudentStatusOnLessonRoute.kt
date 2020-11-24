package com.studentis.routes

import com.studentis.di.components.DaggerRepoComponent
import com.studentis.models.Response
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.joda.time.LocalDate

fun Routing.studentStatusOnLesson() {

    val component = DaggerRepoComponent
            .builder()
            .build()

    val studentStatusOnLessonRepo = component
            .getStudentStatusOnLessonRepo()


    route("/studentis/studentStatusOnLesson") {
        get {

            val queryParams = call.request.queryParameters
            val studentId = queryParams["studentId"]!!.toInt()
            val scheduleId = queryParams["scheduleId"]!!.toInt()
            val dateText = queryParams["date"]!!

            val whereClause = """
                student_id=$studentId and
                schedule_id=$scheduleId and
                date='$dateText'
            """.trimIndent()

            val marks = studentStatusOnLessonRepo.getWhere(whereClause)

            if(marks.isEmpty()) {
                call.respond(
                        HttpStatusCode.NotFound,
                        Response("No statuses are found")
                )
            }
            else {
                call.respond(marks)
            }
        }
    }
}