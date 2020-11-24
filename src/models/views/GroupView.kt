package com.studentis.models.views

import org.joda.time.LocalDate


data class GroupView(
        var groupId: Int,
        var groupName: String,
        var groupSpecialisationCode: String,
        var groupSpecialisationName: String,
        var corpusName: String,
        var dateEnter: LocalDate,
        var dateOut: LocalDate
)