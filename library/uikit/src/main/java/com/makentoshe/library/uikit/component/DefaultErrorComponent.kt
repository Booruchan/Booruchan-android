package com.makentoshe.library.uikit.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.library.feature.throwable.Throwable2ThrowableEntityMapper
import java.nio.channels.UnresolvedAddressException

@Composable
fun DefaultErrorComponent(
    state: ErrorComponentState,
    onClick: () -> Unit = {},
) = ErrorComponent(
    state = state,
    modifier = Modifier.fillMaxWidth().height(128.dp).padding(horizontal = 16.dp, vertical = 8.dp),
    onClick = onClick,
)

@Composable
fun DefaultErrorComponent(
    title: String,
    description: String,
    button: String? = null,
    onClick: () -> Unit = {},
) = ErrorComponent(
    state = ErrorComponentState(title, description, button),
    modifier = Modifier.fillMaxWidth().height(128.dp).padding(horizontal = 16.dp, vertical = 8.dp),
    onClick = onClick,
)

@Composable
fun DefaultErrorComponent(
    throwable: Throwable,
    onClick: () -> Unit = {},
) = DefaultErrorComponent(
    state = errorComponentStateFactory(throwable = throwable),
    onClick = onClick,
)

@Composable
private fun errorComponentStateFactory(throwable: Throwable): ErrorComponentState {
    val throwableEntity = Throwable2ThrowableEntityMapper(LocalContext.current).map(throwable)

    return when (throwable) {
        is UnresolvedAddressException -> {
            ErrorComponentState(throwableEntity.title, throwableEntity.description, "Retry")
        }

        else -> {
            ErrorComponentState(throwableEntity.title, throwableEntity.description, "Retry")
        }
    }
}