package com.example.weathermvvm.ui.main.facility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermvvm.R
import com.example.weathermvvm.bean.FacilityBean

class FacilityAdapter : RecyclerView.Adapter<FacilityAdapter.ViewHolder>() {

    private lateinit var list: ArrayList<FacilityBean>

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFacilityCity: AppCompatTextView = itemView.findViewById(R.id.tc_facility_city)
        val ivCollect: AppCompatImageView = itemView.findViewById(R.id.iv_collect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_facility, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (!this::list.isInitialized) {
            return 0
        }
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFacilityCity.text = list[position].facilitySite
        if (list[position].collect == null || list[position].collect == 0) {
            holder.ivCollect.setImageResource(R.drawable.ic_collect_no)
        } else {
            holder.ivCollect.setImageResource(R.drawable.ic_collect_yes)
        }
        holder.itemView.setOnClickListener { listener?.onClick(position) }

    }

    fun addAllList(list: List<FacilityBean>) {
        if (!this::list.isInitialized) {
            this.list = ArrayList()
        }
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun amendPosition(position: Int) {
        if (list[position].collect == null || list[position].collect == 0) {
            list[position].collect = 1
        } else {
            list[position].collect = 0
        }
        notifyItemChanged(position)
    }
}