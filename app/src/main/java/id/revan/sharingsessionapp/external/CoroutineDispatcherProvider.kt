package id.revan.sharingsessionapp.external

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {

    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}