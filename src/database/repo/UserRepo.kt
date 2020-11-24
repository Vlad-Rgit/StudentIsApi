package com.studentis.database.repo

import com.studentis.database.DatabaseFactory
import com.studentis.database.mappers.UserMapper
import com.studentis.database.repo.interfaces.Repo
import com.studentis.models.User
import com.studentis.utils.onIo
import kotlinx.coroutines.future.await
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

        return onIo {

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
        return onIo {

            val resultSet = DatabaseFactory.pool
                    .sendPreparedStatement("""
                        Select * from $tableName
                            Where user_id=?;
                    """.trimIndent(), listOf(id))
                    .await()
                    .rows

            userMapper.mapRowData(resultSet[0])
        }
    }

    override suspend fun getWhere(where: String): List<User> {
        TODO("Not yet implemented")
    }

    suspend fun getByPasswordHashEmailOrNull(passwordHash: String, email: String): User? {

        return onIo {

            val resultSet = DatabaseFactory.pool
                    .sendPreparedStatement(
                            """
                                Select * from $tableName
                                    Where password_hash=? and email=?;
                            """.trimIndent(),
                            listOf(passwordHash, email))
                    .await()
                    .rows


            if(resultSet.isEmpty())
                null
            else
                userMapper.mapRowData(resultSet[0])
        }
    }
}