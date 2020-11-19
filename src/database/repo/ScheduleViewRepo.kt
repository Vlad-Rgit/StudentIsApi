package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.ScheduleViewMapper
import com.studentis.models.views.ScheduleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleViewRepo
    @Inject constructor() : Repo<ScheduleView, Int> {

    companion object {
        private const val tableName = "main.schedule_view"
    }

    @Inject
    lateinit var scheduleViewMapper: ScheduleViewMapper

    override suspend fun getAll(): List<ScheduleView> {

        return onIo {

            val resultSet = DatabaseFactory.pool
                    .sendQuery(
                            """
                                Select * from $tableName;
                            """.trimIndent()
                    )
                    .await()
                    .rows

            scheduleViewMapper.mapRowData(resultSet)

        }
    }

    override suspend fun getById(id: Int): ScheduleView {
        TODO("Not yet implemented")
    }

    override suspend fun getWhere(where: String): List<ScheduleView> {
        TODO("Not yet implemented")
    }


    suspend fun getByGroupNameOrNull(groupName: String): List<ScheduleView>? {
        return onIo {
            val result = DatabaseFactory.pool
                    .sendPreparedStatement(
                            """
                                Select * from $tableName
                                          where group_name=?;
                            """.trimIndent(),
                            listOf(groupName)
                    )
                    .await()
                    .rows

            if(result.isEmpty())
                null
            else
                scheduleViewMapper.mapRowData(result)
        }
    }


}