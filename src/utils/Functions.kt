package com.studentis.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Run anything on io dispatcher
 */
suspend fun <M> onIo(block: suspend () -> M): M {
    return withContext(Dispatchers.IO) {
        block()
    }
}