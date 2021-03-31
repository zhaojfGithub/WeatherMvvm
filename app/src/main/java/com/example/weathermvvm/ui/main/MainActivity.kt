package com.example.weathermvvm.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.LogUtils
import com.example.weathermvvm.common.ScrollToTop
import com.example.weathermvvm.common.ToastUtil
import com.example.weathermvvm.store.LoginStore
import com.example.weathermvvm.ui.main.facility.FacilityFragment
import com.example.weathermvvm.ui.main.home.HomeFragment
import com.example.weathermvvm.ui.main.min.MinFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseVmActivity<MainViewModel>() {


    override fun getLayoutId() = R.layout.activity_main

    override fun viewModelClass() = MainViewModel::class.java

    protected lateinit var fragments: Map<Int, Fragment>

    override fun initView(savedInstanceState: Bundle?) {
        fragments = mapOf(
                R.id.menu_home to createFragment(HomeFragment::class.java),
                R.id.menu_facility to createFragment(FacilityFragment::class.java),
                R.id.menu_mine to createFragment(MinFragment::class.java)
        )
        showFragment(0)
        bottom_nav.run {
            setOnNavigationItemSelectedListener { menuItem ->
                showFragment(menuItem.itemId)
                true
            }
            setOnNavigationItemReselectedListener { menuItem ->
                val fragment = fragments.entries.find { it.key == menuItem.itemId }?.value
                if (fragment is ScrollToTop) {
                    fragment.scrollToTop()
                }
            }
        }

        if (savedInstanceState == null) {
            val initialItemId = R.id.menu_home
            bottom_nav.selectedItemId = initialItemId
            showFragment(initialItemId)
        }
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            data.observe(this@MainActivity, Observer { ToastUtil.showShort(it.query) })
        }
    }

    private fun createFragment(clazz: Class<out Fragment>): Fragment {
        var fragment = supportFragmentManager.fragments.find { it.javaClass == clazz }
        if (fragment == null) {
            fragment = when (clazz) {
                HomeFragment::class.java -> HomeFragment.newInstance()
                FacilityFragment::class.java -> FacilityFragment.newInstance()
                MinFragment::class.java -> MinFragment.newInstance()
                else -> throw IllegalArgumentException("argument ${clazz.simpleName} is illegal")
            }
        }
        return fragment
    }

    private fun showFragment(menuItemId: Int) {
        val currentFragment = supportFragmentManager.fragments.find {
            it.isVisible && it in fragments.values
        }
        val targetFragment = fragments.entries.find { it.key == menuItemId }?.value
        supportFragmentManager.beginTransaction().apply {
            currentFragment?.let { if (it.isVisible) hide(it) }
            targetFragment?.let {
                if (it.isAdded) show(it) else add(R.id.container, it)
            }
        }.commit()
    }

    override fun onDestroy() {
        bottom_nav.clearAnimation()
        super.onDestroy()

    }
}