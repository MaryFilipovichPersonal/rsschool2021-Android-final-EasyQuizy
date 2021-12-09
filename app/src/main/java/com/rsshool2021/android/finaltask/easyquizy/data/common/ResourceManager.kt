package com.rsshool2021.android.finaltask.easyquizy.data.common

import android.content.Context
import androidx.annotation.StringRes

class ResourceManager(private val context: Context) {

    fun getString(@StringRes resId: Int): String = context.getString(resId)

}