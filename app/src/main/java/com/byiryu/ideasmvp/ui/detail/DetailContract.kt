package com.byiryu.ideasmvp.ui.detail

import android.content.Context
import android.content.SyncAdapterType
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.data.ProductDetail
import com.byiryu.ideasmvp.di.scope.ActivityScoped
import com.byiryu.ideasmvp.di.scope.ApplicationContext
import com.byiryu.ideasmvp.ui.base.BaseContract
import com.byiryu.ideasmvp.ui.base.BasePresenter

interface DetailContract {

    interface View : BaseContract.View{
        fun setProgressSetting(max : Int)

        fun initContents( productDetail: ProductDetail)

    }

    @ActivityScoped
    interface Presenter<T : View> : BaseContract.Presenter<T>{

        fun loadProductDetail( id : Int)

        fun setRecyclerView(adapter: DetailRecyclerContract.View)

        fun setRecyclerModel(adapter : DetailRecyclerContract.Model)

        fun loadInit(context: Context, imageView : ImageView, url : String)

    }


}