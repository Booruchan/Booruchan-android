package com.makentoshe.booruchan.library.feature.throwable

import android.content.Context
import com.makentoshe.booruchan.library.feature.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.nio.channels.UnresolvedAddressException
import javax.inject.Inject

class Throwable2ThrowableEntityMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun map(throwable: Throwable) = when(throwable) {
        is UnresolvedAddressException -> {
            val title = context.getString(R.string.throwable_unresolved_address_title)
            val description = context.getString(R.string.throwable_unresolved_address_description)
            ThrowableEntity(title = title, description = description)
        }
        else -> {
            val title = context.getString(R.string.throwable_undefined_title)
            ThrowableEntity(title = title, description = throwable.toString())
        }
    }
}