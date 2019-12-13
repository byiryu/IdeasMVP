package com.byiryu.ideasmvp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions

import com.byiryu.ideasmvp.R
import com.byiryu.ideasmvp.data.ProductDetail


class DetailRecyclerAdapter
    : RecyclerView.Adapter<DetailViewHolder>(), DetailRecyclerContract.View, DetailRecyclerContract.Model{

    var images = ArrayList<ProductDetail.Images>()
    lateinit var requestManager :RequestManager
    lateinit var requestOptions: RequestOptions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_detail_item, parent, false),
            parent.context, requestManager, requestOptions
        )
    }
    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
            holder.onBind(images[position].url)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun setGlide(requestOptions: RequestOptions, requestManager: RequestManager) {

        this.requestManager = requestManager
        this.requestOptions = requestOptions
    }

    override fun addItems(items: ArrayList<ProductDetail.Images>) {
        this.images.addAll(items)
    }

    override fun clearItems() {
        this.images.clear()
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

}