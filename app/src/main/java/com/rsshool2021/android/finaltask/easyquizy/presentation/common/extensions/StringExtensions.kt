package com.rsshool2021.android.finaltask.easyquizy.presentation.common.extensions

import android.text.Html
import android.text.Spanned

fun String.fromHtml(): String =
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY ).toString()