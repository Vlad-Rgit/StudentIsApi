package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.TeacherViewMapper
import com.studentis.database.repo.interfaces.Repo
import com.studentis.models.dto.TeacherView
import com.studentis.utils.onIo
import kotlinx.coroutines.future.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TeacherViewRepo
    @Inject constructor()
    : Repo<TeacherView, Int> {

    companion object {
        private const val tableName = "main.teacher_view"
    }

    @Inject
    lateinit var teacherViewMapper: TeacherViewMapper

    override suspend fun getAll(): List<TeacherView> {
        return onIo {
            val resultSet = DatabaseFactory.pool
                    .sendQuery(
                            """
                            Select * from $tableName;
                        """.trimIndent()
                    )
                    .await()
                    .rows

            teacherViewMapper.mapRowData(resultSet)
        }
    }

    override suspend fun getById(id: Int): TeacherView {
        TODO("Not yet implemented")
    }

    override suspend fun getWhere(where: String): List<TeacherView> {
        TODO("Not yet implemented")
    }

}