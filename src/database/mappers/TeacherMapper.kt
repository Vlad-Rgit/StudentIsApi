package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.Teacher
import javax.inject.Inject

class TeacherMapper
    @Inject constructor()
    : RowDataMapper<Teacher> {
    override fun mapRowData(row: RowData): Teacher {
        TODO("Not yet implemented")
    }

}