package com.byiryu.ideasmvp.ui.detail


import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.data.ProductDetail
import com.byiryu.ideasmvp.ui.base.BaseRecyclerContract

interface DetailRecyclerContract{

    interface View : BaseRecyclerContract.View{

        fun setGlide(requestOptions: RequestOptions, requestManager: RequestManager)
    }

    interface Model : BaseRecyclerContract.Model<ProductDetail.Images>{
    }
}