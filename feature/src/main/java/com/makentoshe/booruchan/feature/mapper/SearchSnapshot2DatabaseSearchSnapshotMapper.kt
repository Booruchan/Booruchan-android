package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.ActionSearchHistory
import com.makentoshe.booruchan.library.database.entity.DatabaseActionSearchHistory
import javax.inject.Inject

class SearchSnapshot2DatabaseActionSearchHistoryMapper @Inject constructor() {
    fun map(actionSearchHistory: ActionSearchHistory) = DatabaseActionSearchHistory(
        source = actionSearchHistory.source,
        search = actionSearchHistory.search,
        timestamp = actionSearchHistory.timestamp,
    )
}
