package com.studentis.database

import com.github.jasync.sql.db.Configuration
import com.github.jasync.sql.db.ConnectionPoolConfiguration
import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import com.github.jasync.sql.db.postgresql.pool.PostgreSQLConnectionFactory

object DatabaseFactory {
    val pool = PostgreSQLConnectionBuilder
        .createConnectionPool {
            username = "username"
            password = "cortik228"
            host = "localhost"
            port = 5432
            database = "student_is"
        }
}