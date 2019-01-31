package com.example.sbotlevskyi.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sbotlevskyi.myapplication.DeepLinkAdapter.LinkViewHolder
import kotlinx.android.synthetic.main.link_item.view.*

class DeepLinkAdapter(var callback: (String) -> Unit) : RecyclerView.Adapter<LinkViewHolder>() {

    var listLink = mutableListOf<DeepLinkModel>()

    fun updateData(list: List<DeepLinkModel>) {
        listLink = list.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.link_item, parent, false)
        return LinkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listLink.size
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.onBind(listLink[position], callback)
    }

    class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(item: DeepLinkModel, callback: (String) -> Unit) {
            itemView.titleText.text = item.nameLink
            itemView.linkText.text = item.urlDeepLink
            itemView.setOnClickListener {
                callback.invoke(item.urlDeepLink)
            }
        }
    }
}