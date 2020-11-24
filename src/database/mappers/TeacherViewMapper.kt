package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.dto.TeacherView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeacherViewMapper
    @Inject constructor()
    : RowDataMapper<TeacherView> {

    override fun mapRowData(row: RowData): TeacherView {
        return TeacherView(
                userId = row.getInt("user_id")!!,
                email = row.getString("email")!!,
                lastName = row.getString("last_name")!!,
                firstName = row.getString("first_name")!!,
                patronymic = row.getString("patronymic"),
                passportSerie = row.getString("passport_serie")!!,
                passportNumber = row.getString("passport_number")!!,
                educationalTypeId = row.getInt("education_type_id")!!,
                educationalInstitution = row.getString("educational_institution")!!,
                specialisation = row.getString("specialisation")!!,
                auditorium = row.getAs<Short?>("auditorium"),
                educationalTypeName = row.getString("education_type_name")!!
        )
    }

}