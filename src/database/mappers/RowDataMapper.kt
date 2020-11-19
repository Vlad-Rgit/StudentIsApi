package com.studentis.database.mappers

import com.github.jasync.sql.db.ResultSet
import com.github.jasync.sql.db.RowData

interface RowDataMapper<T> {
    fun mapRowData(row: RowData): T
    fun mapRowData(resultSet: ResultSet): List<T> {
        return resultSet.map { this.mapRowData(it) }
    }
}