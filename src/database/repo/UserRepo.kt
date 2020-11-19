package com.studentis.database.repo

import com.github.jasync.sql.db.RowData
import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.UserMapper
import com.studentis.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo
    @Inject constructor()
    : Repo<User, Int> {

    companion object {
        private const val tableName = "main.user"
    }

    @Inject
    lateinit var userMapper: UserMapper

    override suspend fun getAll(): List<User> {

        return withContext(Dispatchers.IO) {

            val resultSet = DatabaseFactory.pool
                    .sendQuery(
                            """
                    Select * from $tableName;
                """.trimIndent()
                    )
                    .await().rows

            userMapper.mapRowData(resultSet)
        }
    }

    override suspend fun getById(id: Int): User {
        TODO("Not yet implemented")
    }

    override suspend fun getWhere(where: String): List<User> {
        TODO("Not yet implemented")
    }

    suspend fun getByPasswordHashOrNull(passwordHash: String): User? {

        return withContext(Dispatchers.IO) {

            val resultSet = DatabaseFactory.pool
                    .sendQuery(
                            """
                                Select * from $tableName
                                    Where password_hash='$passwordHash';
                            """.trimIndent()
                    )
                    .await()
                    .rows


            if(resultSet.isEmpty())
                null
            else
                userMapper.mapRowData(resultSet[0])
        }
    }
}