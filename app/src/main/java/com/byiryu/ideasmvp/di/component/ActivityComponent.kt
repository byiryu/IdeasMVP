package com.byiryu.ideasmvp.di.component

import com.byiryu.ideasmvp.ui.main.MainActivity
import com.byiryu.ideasmvp.di.module.ActivityModule
import com.byiryu.ideasmvp.di.scope.ActivityScoped
import com.byiryu.ideasmvp.ui.detail.DetailActivity
import dagger.Component

@ActivityScoped
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent{

    fun inject(mainActivity: MainActivity)

    fun inject(detailActivity: DetailActivity)

}
