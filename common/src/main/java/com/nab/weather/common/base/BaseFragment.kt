package com.nab.weather.common.base

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VM : ViewModel, T : ViewDataBinding> : Fragment(){

    private var _binding: T? = null
    val binding get() = _binding!!

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getAssociatedViewModel(): VM

    protected abstract fun observeDataChanged()

    protected open fun initView() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val consumed = onInterceptBackPressed()
                    if (!consumed) {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            }
        )
    }

    @IdRes
    open fun getBindingViewModelId(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Resources.ID_NULL
        } else {
            0
        }
    }

    protected open fun onInterceptBackPressed() = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        if (getBindingViewModelId() != Resources.ID_NULL) {
            binding.setVariable(getBindingViewModelId(), getAssociatedViewModel())
        }
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeDataChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
