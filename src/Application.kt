package com.studentis

import com.studentis.database.gson.typeadapters.LocalDateAdapter
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import com.studentis.database.gson.typeadapters.LocalTimeAdapter
import com.studentis.di.components.DaggerAuthencticationComponent
import com.studentis.models.Response
import com.studentis.routes.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.gson.*
import org.joda.time.LocalDate
import org.joda.time.LocalTime


fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val authComponent = DaggerAuthencticationComponent
            .builder()
            .build()


    install(StatusPages) {
        exception<Throwable>() { ex ->
            call.respond(
                HttpStatusCode.InternalServerError,
                Response("Error: ${ex.message}")
            )
        }
    }


    install(Authentication) {

        val jwtService = authComponent.getJwtService()

        jwt("all") {
            verifier(jwtService.verifier)
            validate { jwtCredential ->
                JWTPrincipal(jwtCredential.payload)
            }
        }

        jwt(name = "teacher") {
            verifier(jwtService.verifier)
            validate { jwtCredential ->
                jwtService.validate(jwtCredential, 3)
            }
        }

        jwt(name = "student") {
            verifier(jwtService.verifier)
            validate { jwtCredential ->
                jwtService.validate(jwtCredential, 4)
            }
        }


        jwt(name = "admin") {
            verifier(jwtService.verifier)
            validate { jwtCredential ->
                jwtService.validate(jwtCredential, 5)
            }
        }

    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
            registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        }
    }

    routing {
        authorization()
        users()
        teacherViews()
        schedule()
        groupView()
        educationType()
        teachers()
        students()
        studentStatusOnLesson()
    }
}

