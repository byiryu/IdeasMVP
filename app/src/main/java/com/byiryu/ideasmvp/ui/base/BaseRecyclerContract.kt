package com.byiryu.ideasmvp.ui.base

interface BaseRecyclerContract{

    interface Model<T>{
        fun addItems(items : ArrayList<T> )

        fun clearItems()
    }

    interface View{
        fun notifyAdapter()
    }
}