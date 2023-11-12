package com.makentoshe.booruchan.library.resources

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetStringUseCase @Inject constructor(@ApplicationContext private val context: Context) {
    operator fun invoke(@StringRes id: Int, vararg args: Any): String {
        return context.getString(id, args)
    }
}