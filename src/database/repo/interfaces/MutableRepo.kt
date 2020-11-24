package com.studentis.database.repo.interfaces

interface MutableRepo<T, K>: Repo<T, K> {
    suspend fun add(item: T)
    suspend fun remove(item: T)
    suspend fun update(item: T)
}