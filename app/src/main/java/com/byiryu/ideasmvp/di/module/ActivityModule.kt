package com.byiryu.ideasmvp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
//import com.bumptech.glide.Glide
//import com.bumptech.glide.RequestManager
//import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.di.scope.ActivityScoped
import com.byiryu.ideasmvp.ui.detail.DetailContract
import com.byiryu.ideasmvp.ui.detail.DetailPresenter
import com.byiryu.ideasmvp.ui.detail.DetailRecyclerAdapter
import com.byiryu.ideasmvp.ui.main.MainContract
import com.byiryu.ideasmvp.ui.main.MainPresenter
import com.byiryu.ideasmvp.ui.main.MainRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(appCompatActivity: AppCompatActivity){

    private var appCompatActivity = appCompatActivity

    @Provides
    fun provideContext() : Context {
        return appCompatActivity
    }

    @Provides
    fun provideActivity() : AppCompatActivity{
        return appCompatActivity
    }

    /**
     * Presenter
     */

    @Provides
    @ActivityScoped
    fun provideMainPresenter(presenter: MainPresenter<MainContract.View>)
            : MainContract.Presenter<MainContract.View> {
        return presenter
    }

    @Provides
    @ActivityScoped
    fun provideDetailPresenter(presenter : DetailPresenter<DetailContract.View>)
            : DetailContract.Presenter<DetailContract.View>{
        return presenter
    }



    /**
     * Recycler
     */

    @Provides
    fun provideMainRecyclerAdapter() : MainRecyclerAdapter{
        return MainRecyclerAdapter()
    }

    @Provides
    fun provideDetailRecyclerAdapter() : DetailRecyclerAdapter{
        return DetailRecyclerAdapter()
    }

    /**
     * ViewPager
     */



    /**
     * LayoutManger
     */

    @Provides
    fun provideGridLayoutManager(activity : AppCompatActivity) : GridLayoutManager{
        return GridLayoutManager(activity, 2)
    }

    @Provides
    fun provideLinearLayoutManager(activity: AppCompatActivity) : LinearLayoutManager{
        return LinearLayoutManager(activity)
    }

    /**
     * Glide
     */

    @Provides
    fun provideRequestManger(activity : AppCompatActivity) : RequestManager {
        return Glide.with(activity)
    }

    @Provides
    fun provideRequestOption() : RequestOptions {
        return RequestOptions()
    }

}