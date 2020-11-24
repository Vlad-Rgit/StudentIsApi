package com.studentis.models.dto

data class TeacherView(
        val userId: Int,
        var email: String,
        var lastName: String,
        var firstName: String,
        var patronymic: String?,
        var passportSerie: String,
        var passportNumber: String,
        var educationalInstitution: String,
        var specialisation: String,
        var auditorium: Short?,
        var educationalTypeId: Int,
        val educationalTypeName: String
)