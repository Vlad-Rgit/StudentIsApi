package com.studentis.database.repo

interface Repo<T, K> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: K): T
    suspend fun getWhere(where: String): List<T>
}