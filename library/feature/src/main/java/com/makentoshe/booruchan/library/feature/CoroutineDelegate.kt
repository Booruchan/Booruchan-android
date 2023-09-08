package com.makentoshe.booruchan.library.feature

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface CoroutineDelegate {
    fun coroutineExceptionHandler(action: CoroutineContext.(Throwable) -> Unit): CoroutineExceptionHandler

    fun CoroutineScope.iolaunch(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit,
    )
}

class DefaultCoroutineDelegate: CoroutineDelegate {

    override fun coroutineExceptionHandler(action: CoroutineContext.(Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { coroutineContext, throwable -> coroutineContext.action(throwable) }
    }

    override fun CoroutineScope.iolaunch(context: CoroutineContext, block: suspend CoroutineScope.() -> Unit) {
        this.launch(context = context, start = CoroutineStart.DEFAULT, block = block)
    }

}