package com.byiryu.ideasmvp.ui.main

import android.widget.BaseAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.data.Product
import com.byiryu.ideasmvp.ui.base.BaseRecyclerContract

interface MainRecyclerContract {

    interface View :BaseRecyclerContract.View{

        fun setGlide(requestOptions: RequestOptions, requestManager: RequestManager)

        fun setLoading(loading : Boolean)

        fun getLoading() : Boolean

        fun setOnClickListener(onItemClickListener: MainRecyclerAdapter.OnItemClickListener)
    }

    interface Model : BaseRecyclerContract.Model<Product>
}