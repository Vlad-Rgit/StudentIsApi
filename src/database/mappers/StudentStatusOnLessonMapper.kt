package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.StudentStatusOnLesson
import org.joda.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StudentStatusOnLessonMapper
    @Inject constructor()
    : RowDataMapper<StudentStatusOnLesson> {

    override fun mapRowData(row: RowData): StudentStatusOnLesson {
        return StudentStatusOnLesson(
                studentId = row.getInt("student_id")!!,
                scheduleId = row.getInt("schedule_id")!!,
                studentStatusId = row.getInt("student_status_id")!!,
                date = row.getAs<LocalDate>("date"),
                mark = row.getAs<Short?>("mark")
        )
    }


}