package com.example.weathermvvm.ui.site.inquire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermvvm.R
import com.example.weathermvvm.bean.SiteBean

class InquireSiteAdapter : RecyclerView.Adapter<InquireSiteAdapter.ViewHolder>() {

    private lateinit var list: ArrayList<SiteBean.Place>

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(Name: String, Lat: String, Lng: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_inquire, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        if (!this::list.isInitialized) {
            return 0
        }
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSite.text = list[position].name
        val coordinate = " [ ${list[position].location.lat}  ${list[position].location.lng}]"
        holder.tvCoordinate.text = coordinate
        holder.itemView.setOnClickListener {
            listener!!.onClick(
                    list[position].name,
                    list[position].location.lat.toString(),
                    list[position].location.lng.toString())
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSite: TextView = itemView.findViewById(R.id.tv_site)
        val tvCoordinate: TextView = itemView.findViewById(R.id.tv_coordinate);
    }

    fun addAllList(list: List<SiteBean.Place>) {
        if (!this::list.isInitialized) {
            this.list = ArrayList()
        }
        this.list.clear()
        this.list.addAll(list)
    }
}