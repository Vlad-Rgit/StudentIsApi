package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.GroupViewMapper
import com.studentis.database.repo.interfaces.Repo
import com.studentis.models.views.GroupView
import com.studentis.utils.onIo
import kotlinx.coroutines.future.await
import javax.inject.Inject

class GroupViewRepo
    @Inject constructor()
    : Repo<GroupView, Int> {

    companion object {
        private const val tableName = "main.group_view"
    }

    @Inject
    lateinit var groupViewMapper: GroupViewMapper

    override suspend fun getAll(): List<GroupView> {
        return onIo {

            val resultSet = DatabaseFactory.pool
                    .sendQuery("""
                        Select * from $tableName;
                    """.trimIndent())
                    .await()
                    .rows

            groupViewMapper.mapRowData(resultSet)
        }
    }

    override suspend fun getById(id: Int): GroupView {
        TODO("Not yet implemented")
    }

    override suspend fun getWhere(where: String): List<GroupView> {
        TODO("Not yet implemented")
    }

}