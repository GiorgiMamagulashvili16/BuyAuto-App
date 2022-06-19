package com.example.buyauto_app.domain.util

import android.content.Context
import androidx.annotation.NonNull
import androidx.annotation.StringRes


class StringResourceProvider (
     private val context: Context
) {
    @NonNull
    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return context.getString(resId, *formatArgs)
    }
}