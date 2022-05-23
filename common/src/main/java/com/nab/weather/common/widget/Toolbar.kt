package com.nab.weather.common.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.nab.weather.common.R
import com.nab.weather.extension.setSafeOnClickListener
import dagger.hilt.android.internal.managers.ViewComponentManager

class Toolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var layoutContainer: ConstraintLayout
    private lateinit var layoutBack: View
    private lateinit var tvTitle: TextView
    private lateinit var ivBack: ImageView

    private var title: String? = null
    private var backButtonIcon: Drawable? = null
    private var hideBackButton: Boolean = false
    private var bgColor: Int? = null

    private var onClickBackListener: (() -> Boolean)? = null

    init {
        init(attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun init(attrs: AttributeSet?) {
        val styles = context.obtainStyledAttributes(attrs, R.styleable.ToolbarAttr)
        getAttr(styles)
        generateViews()
        styles.recycle()
    }

    private fun getAttr(a: TypedArray) {
        hideBackButton = a.getBoolean(R.styleable.ToolbarAttr_hide_back_button, false)
        title = a.getString(R.styleable.ToolbarAttr_android_text)
        backButtonIcon = a.getDrawable(R.styleable.ToolbarAttr_back_button_icon)
        bgColor = a.getInt(R.styleable.ToolbarAttr_android_background, -1)
    }

    private fun generateViews() {
        val view = LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this, true)
        layoutContainer = view.findViewById(R.id.layout_container)
        layoutBack = view.findViewById(R.id.layout_back)
        tvTitle = view.findViewById(R.id.tv_title)
        ivBack = view.findViewById(R.id.iv_back)

        bgColor?.let {
            if (it != -1) {
                layoutContainer.setBackgroundColor(it)
            }
        }

        tvTitle.text = title

        layoutBack.run {
            if (hideBackButton) visibility = View.GONE

            backButtonIcon?.let {
                ivBack.setImageDrawable(it)
            }

            setSafeOnClickListener {
                val isHandled = onClickBackListener?.invoke()

                if (onClickBackListener == null || isHandled == false) {
                    val mContext = context
                    val activityContext =
                        if (mContext is ViewComponentManager.FragmentContextWrapper) {
                            mContext.baseContext
                        } else {
                            mContext
                        }
                    (activityContext as Activity).onBackPressed()
                }
            }
        }
    }

    fun setTitleToolbar(title: String) {
        tvTitle.text = title
    }

    fun setOnClickBackListener(onBackListener: () -> Boolean) {
        this.onClickBackListener = onBackListener
    }
}
