package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.Teacher
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TeacherMapper @Inject constructor()
    :RowDataMapper<Teacher> {

    @Inject
    lateinit var userMapper: UserMapper

    override fun mapRowData(row: RowData): Teacher {
        return Teacher(
            userData = userMapper.mapRowData(row),
            educationalTypeId = row.getInt("education_type_id")!!,
            educationalInstitution = row.getString("educational_institution")!!,
            specialisation = row.getString("specialisation")!!,
            auditorium = row.getInt("auditorium")
        )
    }
}