package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.TeacherMapper
import com.studentis.models.Teacher
import kotlinx.coroutines.future.await
import javax.inject.Inject
import javax.inject.Singleton
import javax.xml.crypto.Data


@Singleton
class TeacherRepo @Inject constructor()
    : Repo<Teacher, Int> {

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

}