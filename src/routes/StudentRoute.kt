package com.studentis.routes

import com.studentis.di.components.DaggerRepoComponent
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.students() {
    
    val repoComponent = DaggerRepoComponent
            .builder()
            .build()
    
    val studentRepo = repoComponent.getStudentRepo()
    
    route("/studentis/students") {
        get {
            call.respond(studentRepo.getAll())
        }
        
        get("/{groupId}") { 
            val groupId = call.parameters["groupId"]!!.toInt()
            val whereClause = "group_id=$groupId"
            call.respond(studentRepo.getWhere(whereClause))
        }
    }
    
}