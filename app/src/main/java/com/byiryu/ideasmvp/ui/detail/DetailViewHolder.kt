package com.byiryu.ideasmvp.ui.detail

import android.content.Context
import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.ui.base.BaseViewHolder
import com.byiryu.ideasmvp.utils.CustomGlideRounded
import kotlinx.android.synthetic.main.view_detail_item.view.*
import kotlinx.android.synthetic.main.view_main_item.view.*
import pyxis.uzuki.live.nyancat.NyanCat

class DetailViewHolder constructor(itemView : View, var context : Context, var requestManager: RequestManager,
                                   var requestOptions: RequestOptions ) : BaseViewHolder(itemView){

    fun onBind(url : String){
        NyanCat.e(url)

        requestOptions = requestOptions.transform(CenterCrop(), CustomGlideRounded(context, 25, 0,
            CustomGlideRounded.CornerType.TOP
        ))
        requestManager.load(url)
            .apply(requestOptions)
            .into(itemView.imageview)
    }

}