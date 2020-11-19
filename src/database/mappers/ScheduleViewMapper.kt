package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.views.ScheduleView
import org.joda.time.LocalTime
import java.sql.Time
import java.sql.Timestamp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleViewMapper
    @Inject constructor()
    : RowDataMapper<ScheduleView> {


    override fun mapRowData(row: RowData): ScheduleView {
        return ScheduleView(
                scheduleId = row.getInt("schedule_id")!!,
                subjectId = row.getInt("subject_id")!!,
                subjectName = row.getString("subject_name")!!,
                groupId = row.getInt("group_id")!!,
                groupName = row.getString("group_name")!!,
                teacherId = row.getInt("teacher_id")!!,
                teacherFullName = row.getString("teacher_full_name")!!,
                dayNumber = row.getAs<Short>("day_number"),
                auditorium = row.getAs<Short>("auditorium"),
                timeStart = row.getAs<LocalTime>("time_start"),
                timeEnd = row.getAs<LocalTime>("time_end"),
                isDenominator = row.getBoolean("is_denominator")!!,
                number = row.getAs<Short>("number")
        )
    }

}