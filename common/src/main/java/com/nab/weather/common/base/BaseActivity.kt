package com.nab.weather.common.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel, T : ViewDataBinding> : AppCompatActivity() {

    private var _binding: T? = null
    val binding get() = _binding!!

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getAssociatedViewModel(): VM

    protected abstract fun observeDataChanged()

    protected open fun initView() = Unit

    @IdRes
    open fun getContainerId() = ID_NULL

    @IdRes
    open fun getBindingViewModelId() = ID_NULL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, getLayoutId())
        if (getBindingViewModelId() != ID_NULL) {
            binding.setVariable(getBindingViewModelId(), getAssociatedViewModel())
        }
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        initView()
        observeDataChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val ID_NULL = -1
    }
}
