package com.studentis.models

data class Teacher(
    var userData: User,
    var educationalTypeId: Int,
    var educationalInstitution: String,
    var specialisation: String,
    var auditorium: Int?
)