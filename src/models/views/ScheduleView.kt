package com.studentis.models.views

import org.joda.time.LocalTime


data class ScheduleView(
        val scheduleId: Int,
        val subjectId: Int,
        val subjectName: String,
        val teacherId: Int,
        val teacherFullName: String,
        val groupId: Int,
        val groupName: String,
        val auditorium: Short,
        val dayNumber: Short,
        val timeStart: LocalTime,
        val timeEnd: LocalTime,
        val isDenominator: Boolean,
        val number: Short
)