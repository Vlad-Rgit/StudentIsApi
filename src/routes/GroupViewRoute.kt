package com.studentis.routes

import com.apurebase.kgraphql.KGraphQL
import com.studentis.di.components.DaggerAuthencticationComponent
import com.studentis.di.components.DaggerRepoComponent
import com.studentis.models.GraphQlRequest
import com.studentis.models.views.GroupView
import com.studentis.utils.onIo
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.LocalDate

fun Routing.groupView() {

    val component = DaggerRepoComponent
            .builder()
            .build()

    val groupViewRepo = component.getGroupViewRepo()

    val schema = KGraphQL.schema {

        query("groupView") {
            resolver<List<GroupView>> { ->
                groupViewRepo.getAll()
            }
        }

        type<GroupView>()
        type<LocalDate>() {
            property<String>("date") {
                resolver {
                    it.toString("yyyy-MM-dd")
                }
            }
        }
    }

    route("/studentis/groupView") {

        get {
            call.respond(groupViewRepo.getAll())
        }

        get("/graphQl") {
            val request = call.receive<GraphQlRequest>()
            call.respond(schema.execute(request.query))
        }

        post("/graphQl") {
            val request = call.receive<GraphQlRequest>()
            call.respond(schema.execute(request.query))
        }
    }
}