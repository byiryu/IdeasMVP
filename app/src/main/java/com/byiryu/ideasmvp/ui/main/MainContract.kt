package com.byiryu.ideasmvp.ui.main

import android.content.Context
import android.widget.ImageView
import com.byiryu.ideasmvp.di.scope.ActivityScoped
import com.byiryu.ideasmvp.ui.base.BaseContract

interface MainContract {

    @ActivityScoped
    interface Presenter<T : View >: BaseContract.Presenter<T>{

        fun setRecyclerView(mainRecyclerContract: MainRecyclerContract.View)

        fun setRecyclerModel(mainRecyclerContract: MainRecyclerContract.Model)

        fun getProduct(context : Context, page : Int)

        fun addProduct()

        fun LoadMore()

        fun isLoading() : Boolean

        fun setLoading(loading : Boolean)

    }

    interface View : BaseContract.View{

        fun setTransition(imageView: ImageView)


    }
}