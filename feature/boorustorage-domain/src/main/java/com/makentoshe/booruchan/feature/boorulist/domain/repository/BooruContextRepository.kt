package com.makentoshe.booruchan.feature.boorulist.domain.repository

import com.makentoshe.booruchan.feature.BooruContext
import kotlinx.coroutines.flow.Flow

interface BooruContextRepository {
    suspend fun getBooruContextList(): Flow<List<BooruContext>>

    suspend fun addBooruContext(booruContext: BooruContext)
}


sealed class BooruContextRepositoryException : Exception() {
    data class IdentifierAlreadyExists(val string: String) : BooruContextRepositoryException()
}