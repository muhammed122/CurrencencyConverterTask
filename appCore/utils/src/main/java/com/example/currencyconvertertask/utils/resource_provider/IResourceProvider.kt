package com.example.currencyconvertertask.utils.resource_provider

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.Spannable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.*

interface IResourceProvider {
  fun getColor(@ColorRes resId: Int): Int

  fun getDrawable(@DrawableRes resId: Int): Drawable


  fun getText(@StringRes resId: Int, vararg values: Any): String

  fun getSpannableText(
    @StringRes resId: Int,
    values: String,
    @ColorRes color: Int
  ): Spannable

  fun getLocale(): Locale

  fun isArabicLocale(): Boolean
  fun getSystemResources(): Resources
}