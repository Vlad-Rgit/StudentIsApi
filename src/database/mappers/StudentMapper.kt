package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentMapper
    @Inject
    constructor(): RowDataMapper<Student> {
    override fun mapRowData(row: RowData): Student {
        return Student(
                userId = row.getInt("user_id"),
                roleId = row.getInt("role_id")!!,
                groupId = row.getInt("group_id")!!,
                email = row.getString("email")!!,
                passwordHash = row.getString("password_hash")!!,
                lastName = row.getString("last_name")!!,
                firstName = row.getString("first_name")!!,
                patronymic = row.getString("patronymic"),
                passportSerie = row.getString("passport_serie")!!,
                passportNumber = row.getString("passport_number")!!
        )
    }

}