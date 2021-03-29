package com.example.weathermvvm.ui.main.facility


import android.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmFragment
import com.example.weathermvvm.common.LogUtils
import com.example.weathermvvm.common.ToastUtil
import kotlinx.android.synthetic.main.activity_site_list.*
import kotlinx.android.synthetic.main.activity_site_list.toolbar
import kotlinx.android.synthetic.main.fragment_facility.*


class FacilityFragment : BaseVmFragment<FacilityViewModel>() {

    override fun viewModelClass() = FacilityViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_facility

    private lateinit var adapter: FacilityAdapter

    companion object {
        fun newInstance() = FacilityFragment()
    }

    override fun initView() {
        super.initView()
        toolbar.apply {
            title = "城市列表"
            setTitleTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
            setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.blue))
            inflateMenu(R.menu.ficality_menu)
            setOnMenuItemClickListener {
                return@setOnMenuItemClickListener true
            }
        }
        adapter = FacilityAdapter()
        rv_facility.layoutManager = LinearLayoutManager(activity)
        rv_facility.adapter = adapter
        adapter.setOnItemClickListener(object : FacilityAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                mViewModel.setUserFacility(position);
            }
        })
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        mViewModel.getAllFacility()
    }

    override fun observe() {
        super.observe()
        mViewModel.apply {
            list.observe(this@FacilityFragment, Observer {
                adapter.addAllList(it)
            })
            setFacility.observe(this@FacilityFragment, Observer {
                ToastUtil.showShort(it.msg)
                adapter.amendPosition(position)
            })
            isLogin.observe(this@FacilityFragment, Observer {
                if (!it) loginDialog()
            })
        }
    }

    private fun loginDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(activity)
                .setTitle("登录")
                .setMessage("只有进行登录才可以进行收藏！！！")
                .setPositiveButton("确定") { dialog1, _ ->
                    dialog1.dismiss()
                }
        dialog.show()
    }
}
