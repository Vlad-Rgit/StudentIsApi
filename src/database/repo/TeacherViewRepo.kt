package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.TeacherViewMapper
import com.studentis.models.dto.TeacherView
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

        val resultSet = DatabaseFactory.pool
                .sendQuery(
                        """
                            Select * from $tableName;
                        """.trimIndent()
                )
                .await()
                .rows

        return teacherViewMapper.mapRowData(resultSet)
    }

    override suspend fun getById(id: Int): TeacherView {
        TODO("Not yet implemented")
    }

    override suspend fun getWhere(where: String): List<TeacherView> {
        TODO("Not yet implemented")
    }

}