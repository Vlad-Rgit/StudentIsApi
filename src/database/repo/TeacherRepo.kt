package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.TeacherMapper
import com.studentis.database.repo.interfaces.MutableRepo
import com.studentis.database.repo.interfaces.Repo
import com.studentis.models.Teacher
import com.studentis.models.dto.TeacherView
import com.studentis.utils.onIo
import kotlinx.coroutines.future.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TeacherRepo @Inject constructor()
    : MutableRepo<Teacher, Int> {

    companion object {
        private const val tableName = "main.teacher"
    }

    @Inject
    lateinit var teacherMapper: TeacherMapper

    override suspend fun getAll(): List<Teacher> {
        return onIo {
            val resultSet = DatabaseFactory.pool
                    .sendQuery(
                            """
                                Select * from $tableName;
                            """.trimIndent())
                    .await().rows

            teacherMapper.mapRowData(resultSet)
        }
    }

    override suspend fun getById(id: Int): Teacher {
        TODO("Not yet implemented")
    }

    override suspend fun getWhere(where: String): List<Teacher> {
        TODO("Not yet implemented")
    }

    override suspend fun add(item: Teacher) {
        onIo {
            DatabaseFactory.pool
                    .sendQuery("""
                        Insert into $tableName (
                        role_id,
                        email,
                        password_hash,
                        last_name,
                        first_name,
                        patronymic,
                        passport_serie,
                        passport_number,
                        education_type_id,
                        educational_institution,
                        specialisation,
                        auditorium) values(
                        ${item.roleId},
                        '${item.email}',
                        '${item.passwordHash}',
                        '${item.lastName}',
                        '${item.firstName}',
                        '${item.patronymic}',
                        '${item.passportSerie}',
                        '${item.passportNumber}',
                        ${item.educationalTypeId},
                        '${item.educationalInstitution}',
                        '${item.specialisation}',
                        ${item.auditorium});
                    """.trimIndent())
                    .await()
        }
    }

    override suspend fun remove(item: Teacher) {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: Teacher) {
        TODO("Not yet implemented")
    }

}