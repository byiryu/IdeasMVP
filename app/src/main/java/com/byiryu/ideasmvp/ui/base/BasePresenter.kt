package com.byiryu.ideasmvp.ui.base

import javax.inject.Inject

open class BasePresenter <T : BaseContract.View > @Inject constructor() : BaseContract.Presenter<T>{

    var view : T? = null

    override fun onAttach(view : T) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    fun isViewAttached() : Boolean{
        return this.view != null
    }
    fun getMvpView(): T {
        return this.view!!
    }

}