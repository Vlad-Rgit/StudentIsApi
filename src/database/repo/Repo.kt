package com.studentis.database.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface Repo<T, K> {

    /**
     * Run anything on io dispatcher
     */
    suspend fun <M> onIo(block: suspend () -> M): M {
        return withContext(Dispatchers.IO) {
            block()
        }
    }

    suspend fun getAll(): List<T>
    suspend fun getById(id: K): T
    suspend fun getWhere(where: String): List<T>
}