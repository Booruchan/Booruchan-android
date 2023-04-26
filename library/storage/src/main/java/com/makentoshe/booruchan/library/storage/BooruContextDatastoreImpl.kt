package com.makentoshe.booruchan.library.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.makentoshe.booruchan.feature.boorulist.domain.DatastoredBooruContext
import com.makentoshe.booruchan.feature.boorulist.domain.storage.BooruContextDatastore
import com.makentoshe.booruchan.feature.boorulist.domain.storage.BooruContextDatastoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class BooruContextDatastoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
): BooruContextDatastore {

    override fun getBooruContextList(): Flow<List<DatastoredBooruContext>> {
        return dataStore.data.mapNotNull { preferences ->
            preferences[BOORULIST_IDENTIFIERS]?.mapNotNull { identifier ->
                preferences[stringPreferencesKey(identifier)]
            }?.map { Json.decodeFromString(it) }
        }
    }

    override suspend fun addBooruContext(datastoredBooruContext: DatastoredBooruContext) {
        dataStore.edit { preferences ->

            val identifiers = preferences[BOORULIST_IDENTIFIERS]
            val identifier = datastoredBooruContext.host

            if (identifiers?.contains(identifier) == true) {
                throw BooruContextDatastoreException.IdentifierAlreadyExists(identifier)
            }

            preferences[BOORULIST_IDENTIFIERS] = identifiers?.plus(identifier) ?: setOf(identifier)
            preferences[stringPreferencesKey(identifier)] = Json.encodeToString(datastoredBooruContext)
        }
    }

    override suspend fun getBooruContext(booruContextUrl: String): Flow<DatastoredBooruContext> {
        return dataStore.data.mapNotNull { preferences ->
            preferences[stringPreferencesKey(booruContextUrl)] ?:
                throw BooruContextDatastoreException.IdentifierNotFound(booruContextUrl )
        }.map { Json.decodeFromString(it) }
    }

    companion object {
        private val BOORULIST_IDENTIFIERS = stringSetPreferencesKey("AllBoorulistIdentifiers")
    }
}