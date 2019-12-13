package com.byiryu.ideasmvp.ui.main

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.data.Product
import com.byiryu.ideasmvp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.view_main_item.view.*

class MainViewHolder constructor(itemView : View, var context : Context, var requestManager: RequestManager,
                                 var requestOptions: RequestOptions, var onItemClickListener: MainRecyclerAdapter.OnItemClickListener
) : BaseViewHolder(itemView) {


    fun onBind(product: Product, pos :Int){

        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(25))
        requestManager.load(product.thumbnail)
            .apply(requestOptions)
            .into(itemView.product_img)


        itemView.product_title.text = product.title
        itemView.product_seller.text = product.seller
        itemView.product_img.transitionName = product.id.toString()

        itemView.contents.setOnClickListener {
            onItemClickListener.onItemClick(pos, itemView.product_img)
        }


    }

}