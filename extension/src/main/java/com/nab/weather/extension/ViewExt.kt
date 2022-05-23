package com.nab.weather.extension

import android.content.Context
import android.os.SystemClock
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun SpannableStringBuilder.setTextColor(
    context: Context,
    resColor: Int,
    text: String,
    subText: String
) {
    setSpan(
        ForegroundColorSpan(context.getColorExt(resColor)),
        text.indexOf(subText),
        text.indexOf(subText) + subText.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

fun Context.getColorExt(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun TextView.setTextColorExt(@ColorRes id: Int) = setTextColor(context.getColorExt(id))

fun TextView.setSpannedText(
    subText1: String,
    subText2: String,
    @ColorRes subText2ColorId: Int
) {
    val text = "$subText1 $subText2"

    val stringBuilder = SpannableStringBuilder(text).apply {
        setTextColor(context, subText2ColorId, text, subText2)
    }

    movementMethod = LinkMovementMethod.getInstance()
    this.text = stringBuilder
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}
