package com.max.kkday.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun Activity?.hideKeyboard() {
    this?.let {
        val inputMethodManager =
            it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val windowToken = it.window?.decorView?.windowToken
        inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Activity?.showKeyboard(editText: EditText?) {
    this?.let { activity ->
        editText?.let { editText ->
            activity.window.decorView.postDelayed({
                val inputMethodManager =
                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
            }, 100)
        }
    }
}