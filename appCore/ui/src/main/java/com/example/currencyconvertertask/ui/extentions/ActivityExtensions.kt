package com.example.currencyconvertertask.ui.extentions

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.currencyconvertertask.ui.R
import com.example.currencyconvertertask.ui.databinding.ConnectionErrorLayoutBinding
import com.example.currencyconvertertask.ui.databinding.ErrorDialogLayoutBinding
import kotlinx.coroutines.flow.SharedFlow

fun AppCompatActivity.showNoInternetAlert(noInternetAction: (() -> Unit)?) {
    val dialogBuilder: AlertDialog.Builder =
        AlertDialog.Builder(this, R.style.alert_dialog_theme)
    val binding =
        ConnectionErrorLayoutBinding.inflate(LayoutInflater.from(this))
    dialogBuilder.setView(binding.root)
    dialogBuilder.setCancelable(false)
    val alertDialog: AlertDialog = dialogBuilder.create()
    alertDialog.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT
    )

    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    binding.reloadBtn.setOnClickListener {
        alertDialog.dismiss()
        noInternetAction?.invoke()
//        reload(error)
//        noConnectionErrorAppears = AtomicBoolean(false)
    }

    alertDialog.show()
}

fun AppCompatActivity.showErrorDialog(title: String, message: String) {
    val dialogBuilder: android.app.AlertDialog.Builder =
        android.app.AlertDialog.Builder(this)
    val binding =
        ErrorDialogLayoutBinding.inflate(LayoutInflater.from(this))
    dialogBuilder.setView(binding.root)
    binding.message.text = message
    binding.title.text = title
    val alertDialog: android.app.AlertDialog = dialogBuilder.create()
    alertDialog.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT
    )
    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    alertDialog.setCancelable(false)

    binding.okBtn.setOnClickListener {
        alertDialog.dismiss()
    }


    alertDialog.show()
}

fun Activity.hideKeyboard() {
    var view = currentFocus
    if (view == null) view = View(this)
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun <T> AppCompatActivity.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(this) {
        block.invoke(it)
    }
}

fun <T> AppCompatActivity.collect(sharedFlow: SharedFlow<T>, block: (T) -> Unit) {
    this.lifecycleScope.launchWhenStarted {
        sharedFlow.collect {
            block(it)
        }
    }
}