package com.byiryu.ideasmvp.ui.main


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.Explode
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byiryu.ideasmvp.R
import com.byiryu.ideasmvp.api.ApiService
import com.byiryu.ideasmvp.ui.base.BaseActivity
import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_main.*
import pyxis.uzuki.live.nyancat.NyanCat


class MainActivity : BaseActivity(), MainContract.View{


    @Inject
    lateinit var mainPresenter : MainContract.Presenter<MainContract.View>

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    lateinit var options : ActivityOptionsCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.inject(this)

        mainPresenter.onAttach(this)

        init()

    }
    override fun init() {

        mainPresenter.setRecyclerModel(mainRecyclerAdapter)
        mainPresenter.setRecyclerView(mainRecyclerAdapter)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = gridLayoutManager
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if(mainRecyclerAdapter.itemCount-1 == position)
                    2
                else
                    1
            }
        }

        mainPresenter.getProduct(this, 1)
        recyclerView.adapter = mainRecyclerAdapter
        mainRecyclerAdapter.notifyDataSetChanged()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                var visibleItemCount = (recyclerView.layoutManager as GridLayoutManager).childCount
                var totalItemCount = (recyclerView.layoutManager as GridLayoutManager).itemCount
                var pastVisibleItems = (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

                if(!mainPresenter.isLoading() && (visibleItemCount + pastVisibleItems) >= totalItemCount){
                    mainPresenter.LoadMore()
                    mainPresenter.setLoading(true)
                }

            }
        })

    }

    @SuppressLint("NewApi")
    override fun setTransition(imageView: ImageView) {
        options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,imageView, imageView.transitionName )
    }

    override fun goActivity(intent: Intent) {
        startActivity(intent, options.toBundle())
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDetach()
    }





}
