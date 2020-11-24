package com.studentis.models

data class Student(
        val userId: Int? = null,
        var roleId: Int,
        var groupId: Int,
        var email: String,
        var passwordHash: String,
        var lastName: String,
        var firstName: String,
        var patronymic: String?,
        var passportSerie: String,
        var passportNumber: String
)