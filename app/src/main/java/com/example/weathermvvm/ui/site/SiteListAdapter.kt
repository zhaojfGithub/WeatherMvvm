package com.example.weathermvvm.ui.site

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermvvm.R
import com.example.weathermvvm.bean.AllSiteBean


class SiteListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var list: ArrayList<AllSiteBean>

    private val ITEM = 0
    private val ITEM_FOOTER = 1

    private var listener: OnItemClickListener? = null
    private var longClickListener: OnItemLongClickListener? = null

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onLongClick(id: Long?, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setOnItemLongClickListener(longClickListener: OnItemLongClickListener?) {
        this.longClickListener = longClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (!this::list.isInitialized){
            ITEM_FOOTER
        } else if (position == list.size) {
            ITEM_FOOTER
        } else {
            ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM) {
            return itemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_site_list, parent, false));
        } else {
            return itemFooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_site_list_footer, parent, false));
        }
    }

    override fun getItemCount(): Int {
        if (!this::list.isInitialized) {
            return 1
        }else if (list.size == 0){
            return 1
        }
        return list.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is itemViewHolder) {
            bindItemViewHolder(holder, position)
        } else {
            binItemFooterViewHolder((holder as itemFooterViewHolder))
        }
    }

    private fun bindItemViewHolder(holder: itemViewHolder, position: Int) {
        holder.tvSiteListName.setText(list[position].site)
        holder.tvSiteListLat.text = list[position].lat
        holder.tvSiteListLng.text = list[position].lng
        holder.itemView.setOnLongClickListener {
            longClickListener?.onLongClick(list[position].id, position)
            true
        }
    }

    private fun binItemFooterViewHolder(holder: itemFooterViewHolder) {
        holder.btSiteAdd.setOnClickListener { v ->
            listener?.onClick(-1)
        }
        holder.btSiteCompile.setOnClickListener { v ->
            listener?.onClick(-2)
        }
    }

    private class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSiteListName: TextView = itemView.findViewById(R.id.tv_site_list_name)
        val tvSiteListLat: TextView = itemView.findViewById(R.id.tv_site_list_lat)
        val tvSiteListLng: TextView = itemView.findViewById(R.id.tv_site_list_lng)

    }

    private class itemFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btSiteAdd: Button = itemView.findViewById(R.id.bt_site_add)
        val btSiteCompile: Button = itemView.findViewById(R.id.bt_site_compile)
    }

    fun addAllList(list: ArrayList<AllSiteBean>) {
        if (!this::list.isInitialized) {
            this.list = ArrayList()
        }
        this.list.clear()
        this.list.addAll(list)
    }



    fun removeList(option: Int) {
        list.removeAt(option)
    }
}