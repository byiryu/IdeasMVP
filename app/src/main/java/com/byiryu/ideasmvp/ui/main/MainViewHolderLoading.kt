package com.byiryu.ideasmvp.ui.main

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.byiryu.ideasmvp.R
import com.byiryu.ideasmvp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.view_main_last.view.*
import pyxis.uzuki.live.nyancat.NyanCat

class MainViewHolderLoading constructor(itemView : View, var context: Context) : BaseViewHolder(itemView){


    fun showLoading(){
        if(itemView.imageview_like_progress.animation == null){
            var animation = AnimationUtils.loadAnimation(context, R.anim.progress_anim)
            itemView.imageview_like_progress.startAnimation(animation)
            itemView.imageview_like_progress.animation = animation
        }
    }

    fun dismissLoading(){
        itemView.imageview_like_progress.clearAnimation()
        itemView.imageview_like_progress.animation = null
    }

}