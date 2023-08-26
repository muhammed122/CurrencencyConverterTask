package com.example.currencyconvertertask.ui.base

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.currencyconvertertask.ui.databinding.ProgressLoadingLayoutBinding
import com.example.currencyconvertertask.ui.extentions.hide
import com.example.currencyconvertertask.ui.extentions.show

class LoadingDialog(
  private val activity: Activity?
) {

  private var _binding: ProgressLoadingLayoutBinding? = null
  private val binding get() = checkNotNull(_binding)

  private var _dialog: Dialog? = null
  private val dialog get() = checkNotNull(_dialog)

  init {
    initViewBinding()
    initDialog()
  }

  private fun initViewBinding() {
    _binding = ProgressLoadingLayoutBinding.inflate(LayoutInflater.from(activity))
  }

  private fun initDialog() {
    _dialog = Dialog(checkNotNull(activity)).apply {
      window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      setContentView(binding.root)
      setCancelable(false)
      setCanceledOnTouchOutside(false)
    }
  }

  fun showDialog(hint: String? = null) {
    if (activity != null && !activity.isFinishing) {
      hideDialog()

      bindHintText(hint)
      dialog.show()
    }
  }

  private fun bindHintText(hint: String?) {
    hint?.let {
      if (it.isNotEmpty()) {
        apply {
          binding.tvHint.show()
          binding.tvHint.text = it
        }
      } else {
        binding.tvHint.hide()
      }
    } ?: run { binding.tvHint.hide() }
  }

  fun hideDialog() {
    if (activity != null && !activity.isFinishing && dialog.isShowing) {
      dialog.dismiss()
    }
  }

  fun clean() {
    dialog.dismiss()
    _binding = null
    _dialog = null
  }
}