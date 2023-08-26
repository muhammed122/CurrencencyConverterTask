package com.example.currencyconvertertask.utils.resource_provider

import android.content.Context
import android.content.res.Resources
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.currencyconvertertask.utils.enums.Languages
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject


class ResourceProvider @Inject constructor(@ApplicationContext private val context: Context) :
    IResourceProvider {

    override fun getColor(@ColorRes resId: Int) = ContextCompat.getColor(context, resId)

    override fun getDrawable(@DrawableRes resId: Int) =
        checkNotNull(ContextCompat.getDrawable(context, resId))

    override fun getText(@StringRes resId: Int, vararg values: Any) =
        context.getString(resId, *values)

    override fun getSpannableText(
        @StringRes resId: Int,
        values: String,
        @ColorRes color: Int
    ): Spannable {
        val string = getText(resId)
        val spannable = SpannableString(values)
        spannable.setSpan(
            ForegroundColorSpan(getColor(color)),
            0,
            values.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        return SpannableStringBuilder().append(string).append(spannable)
    }

    override fun getLocale(): Locale =
        context.resources.configuration.locales.get(0)

    override fun isArabicLocale() = getLocale().language == Languages.ARABIC.value

    override fun getSystemResources(): Resources = context.resources
}