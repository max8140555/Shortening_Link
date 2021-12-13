package com.max.kkday.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun Context?.copyText(text: String) {
    val cm: ClipboardManager? =
        this?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clipData = ClipData.newPlainText("text",text)
    cm?.setPrimaryClip(clipData)
}