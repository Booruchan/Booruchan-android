package com.makentoshe.booruchan.feature.entity

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Entity for storing source and search preferences */
data class ActionSearchHistory(
    val source: String,
    val search: String,
    val timestamp: String = System.currentTimeMillis().let(::Date).let { date ->
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).format(date)
    },
)
