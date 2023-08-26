package com.example.currencyconvertertask.ui.extentions

import android.content.Context
import android.text.InputFilter
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.getTextOrNull(): String? =
    this.text?.takeUnless { it.isEmpty() || it.isBlank() }?.trim()?.toString()

fun EditText.showKeyboard() {
    isFocusable = true
    isFocusableInTouchMode = true
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

