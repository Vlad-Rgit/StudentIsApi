package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.StudentStatusOnLessonMapper
import com.studentis.database.repo.interfaces.Repo
import com.studentis.models.StudentStatusOnLesson
import com.studentis.utils.onIo
import kotlinx.coroutines.future.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentStatusOnLessonRepo
   @Inject constructor(): Repo<StudentStatusOnLesson, Int> {

    companion object {
        private const val tableName = "main.student_status_on_lesson"
    }

    @Inject
    lateinit var studentStatusOnLessonMapper: StudentStatusOnLessonMapper

    override suspend fun getAll(): List<StudentStatusOnLesson> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): StudentStatusOnLesson {
        TODO("Not yet implemented")
    }

    override suspend fun getWhere(where: String): List<StudentStatusOnLesson> {
        return onIo {

            val resultSet = DatabaseFactory.pool
                    .sendQuery("""
                        Select * from $tableName
                            Where $where;
                    """.trimIndent())
                    .await()
                    .rows

            if(resultSet.isEmpty())
                emptyList<StudentStatusOnLesson>()
            else
                studentStatusOnLessonMapper.mapRowData(resultSet)
        }
    }


}