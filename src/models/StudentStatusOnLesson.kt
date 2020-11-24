package com.studentis.models

import org.joda.time.LocalDate

data class StudentStatusOnLesson(
        val studentId: Int,
        val scheduleId: Int,
        val studentStatusId: Int,
        val date: LocalDate,
        val mark: Short?
)