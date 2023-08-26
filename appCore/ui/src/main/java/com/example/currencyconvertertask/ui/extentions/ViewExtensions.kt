package com.example.currencyconvertertask.ui.extentions

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.doOnTextChanged
import com.example.currencyconvertertask.ui.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

fun View.show() {
    if (visibility == View.VISIBLE) return

    visibility = View.VISIBLE
    if (this is Group) {
        this.requestLayout()
    }
}

fun View.hide() {
    if (visibility == View.GONE) return

    visibility = View.GONE
    if (this is Group) {
        this.requestLayout()
    }
}

fun View.invisible() {
    if (visibility == View.INVISIBLE) return

    visibility = View.INVISIBLE
    if (this is Group) {
        this.requestLayout()
    }
}

fun View.goneUnless(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
    if (this is Group) {
        this.requestLayout()
    }
}

fun ImageView.drawCircle(backgroundColor: String, borderColor: String? = null) {
    val shape = GradientDrawable()
    shape.shape = GradientDrawable.OVAL
    shape.cornerRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

    shape.setColor(Color.parseColor(backgroundColor))

    borderColor?.let {
        shape.setStroke(10, Color.parseColor(it))
    }

    background = shape
}

fun ImageView.setTint(@ColorRes id: Int) =
    setColorFilter(ContextCompat.getColor(context, id), PorterDuff.Mode.SRC_IN)

fun View.enable() {
    isEnabled = true
    alpha = 1f
}

fun View.disable() {
    isEnabled = false
    alpha = 0.5f
}

fun View.showSnackBar(
    message: String,
    retryActionName: String? = null,
    action: (() -> Unit)? = null
) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

    action?.let {
        snackBar.setAction(retryActionName) {
            it()
        }
    }

    snackBar.show()
}




