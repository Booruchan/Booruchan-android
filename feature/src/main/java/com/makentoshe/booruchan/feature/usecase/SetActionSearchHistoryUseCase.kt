package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.feature.entity.ActionSearchHistory
import com.makentoshe.booruchan.feature.mapper.SearchSnapshot2DatabaseActionSearchHistoryMapper
import com.makentoshe.booruchan.library.database.HistoryDatabase
import javax.inject.Inject

class SetActionSearchHistoryUseCase @Inject constructor(
    private val historyDatabase: HistoryDatabase,
    private val mapper: SearchSnapshot2DatabaseActionSearchHistoryMapper,
) {
    suspend operator fun invoke(actionSearchHistory: ActionSearchHistory) {
        val searchActionHistory = mapper.map(actionSearchHistory)
        historyDatabase.actionHistoryDao().insert(searchActionHistory)
    }

}
