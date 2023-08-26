package com.example.currencyconvertertask.ui.extentions

import android.content.Context
import android.widget.Toast
import com.example.currencyconvertertask.ui.R

fun Context.showMessage(
    message: String?
) {
    Toast.makeText(
        this, message ?: resources.getString(R.string.some_error),
        Toast.LENGTH_SHORT
    ).show()
}