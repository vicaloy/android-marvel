package com.example.common.coroutines

import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeCatching(
    tryBlock: suspend () -> T,
    catchBlock: (Throwable) -> Unit
) {
    try {
        tryBlock()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        catchBlock(e)
    }
}