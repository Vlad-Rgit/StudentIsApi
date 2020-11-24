package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.StudentMapper
import com.studentis.database.repo.interfaces.Repo
import com.studentis.models.Student
import com.studentis.utils.onIo
import kotlinx.coroutines.future.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepo
    @Inject constructor()
    : Repo<Student, Int> {

    companion object {
        private const val tableName = "main.student"
    }

    @Inject
    lateinit var studentMapper: StudentMapper

    override suspend fun getAll(): List<Student> {
        return onIo {

            val resultSet = DatabaseFactory.pool
                    .sendQuery("""
                        Select * from $tableName;
                    """.trimIndent())
                    .await()
                    .rows

            studentMapper.mapRowData(resultSet)
        }
    }

    override suspend fun getById(id: Int): Student {
        return onIo {

            val resultSet = DatabaseFactory.pool
                    .sendPreparedStatement("""
                        Select * from $tableName
                            Where user_id=?;
                    """.trimIndent(), listOf(id))
                    .await()
                    .rows

            studentMapper.mapRowData(resultSet[0])
        }
    }

    override suspend fun getWhere(where: String): List<Student> {
        return onIo {

            val resultSet = DatabaseFactory.pool
                    .sendPreparedStatement("""
                        Select * from $tableName
                            Where ${where};
                    """.trimIndent())
                    .await()
                    .rows

            studentMapper.mapRowData(resultSet)
        }
    }

}