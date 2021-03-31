package com.example.weathermvvm.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.weathermvvm.common.LogUtils
import com.example.weathermvvm.util.showProgress

abstract class BaseFragment : Fragment() {
    protected var lazyLoaded = false
    protected var mProgressDialog: Dialog? = null
    protected var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
        if (savedInstanceState == null) {
            initData()
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        if (!lazyLoaded) {
            lazyLoadData()
            lazyLoaded = true
        } else {
            initLoginData()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgress()
    }

    open fun initLoginData() {}

    open fun lazyLoadData() {}

    open fun initData() {}

    open fun observe() {}

    open fun initView() {}

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    open fun showDialog() {
        dismissProgress()
        mProgressDialog = mContext?.let { showProgress(it) }
    }

    open fun dismissProgress() {
        mProgressDialog?.dismiss()
        mProgressDialog = null
    }
}