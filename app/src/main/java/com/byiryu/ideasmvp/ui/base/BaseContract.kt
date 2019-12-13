package com.byiryu.ideasmvp.ui.base

import android.content.Intent
import androidx.annotation.StringRes

interface BaseContract{

    interface  Presenter<T : View>{

        fun onAttach(view : T)

        fun onDetach()

    }

    interface View{

        fun showMsg(message : String)

        fun showMsg(@StringRes res: Int)

        fun goActivity(intent : Intent)

        fun init()
    }
}