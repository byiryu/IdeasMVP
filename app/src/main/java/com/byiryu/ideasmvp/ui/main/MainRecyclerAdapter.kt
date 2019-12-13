package com.byiryu.ideasmvp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.R
import com.byiryu.ideasmvp.data.Product
import pyxis.uzuki.live.nyancat.NyanCat

class MainRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() , MainRecyclerContract.Model, MainRecyclerContract.View{


    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    private lateinit var requestOptions: RequestOptions
    private lateinit var requestManager: RequestManager
    private lateinit var onItemClickListener: OnItemClickListener

    private var items : ArrayList<Product> = ArrayList()

    private var isLoading = false

    override fun getItemViewType(position: Int): Int {
        return if(items.size <= position) {
            VIEW_TYPE_LOADING
        }else {
            VIEW_TYPE_ITEM
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if(viewType == VIEW_TYPE_ITEM){
            MainViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_main_item, parent, false),
                parent.context, requestManager, requestOptions, onItemClickListener
            )
        }else{
            MainViewHolderLoading(
                LayoutInflater.from(parent.context).inflate(R.layout.view_main_last, parent, false),
                parent.context
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MainViewHolder){
            holder.onBind(items[position], position)
        }else if(holder is MainViewHolderLoading){
            holder.showLoading()
        }

    }

    override fun getItemCount(): Int {
        return if(items.size == 0)
            0
        else
            items.size+1

    }

    override fun addItems(items: ArrayList<Product>) {
        this.items.addAll(items)
    }

    override fun clearItems() {
        this.items.clear()
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun setLoading(loading: Boolean) {
        this.isLoading = loading
    }

    override fun getLoading(): Boolean {
        return this.isLoading
    }

    override fun setGlide(requestOptions: RequestOptions, requestManager: RequestManager) {
        this.requestOptions = requestOptions
        this.requestManager = requestManager
    }

    override fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int, imageView: ImageView)
    }

}