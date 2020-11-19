package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class UserMapper
    @Inject constructor()
    : RowDataMapper<User> {

    override fun mapRowData(row: RowData): User {
        return User(
            userId = row.getInt("user_id"),
            roleId = row.getInt("role_id")!!,
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