package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.EducationTypeMapper
import com.studentis.database.repo.interfaces.Repo
import com.studentis.models.EducationType
import com.studentis.utils.onIo
import kotlinx.coroutines.future.await
import javax.inject.Inject

class EducationTypeRepo
    @Inject constructor(): Repo<EducationType, Int> {


    companion object {
        private const val tableName = "main.education_type"
    }

    @Inject
    lateinit var educationTypeMapper: EducationTypeMapper

    override suspend fun getAll(): List<EducationType> {
        return onIo {
            val resultSet = DatabaseFactory.pool
                    .sendQuery("""
                        Select * from $tableName;
                    """.trimIndent())
                    .await()
                    .rows

            educationTypeMapper.mapRowData(resultSet)
        }
    }

    override suspend fun getById(id: Int): EducationType {
        TODO("Not yet implemented")
    }

    override suspend fun getWhere(where: String): List<EducationType> {
        TODO("Not yet implemented")
    }

}