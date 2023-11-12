package com.makentoshe.library.uikit.component

import androidx.compose.runtime.Immutable

@Immutable
data class ErrorComponentState(
    val title: String,
    val description: String,
    val button: String?,
)