package com.byiryu.ideasmvp.ui.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.byiryu.ideasmvp.IdeasApplication
import com.byiryu.ideasmvp.di.component.ActivityComponent
import com.byiryu.ideasmvp.di.component.DaggerActivityComponent
import com.byiryu.ideasmvp.di.module.ActivityModule

open class BaseActivity : AppCompatActivity(), BaseContract.View{

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent= DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as IdeasApplication).getComponent())
            .build()
    }

    override fun showMsg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMsg(res: Int) {
        showMsg(getString(res))
    }

    override fun init() {

    }

    override fun goActivity(intent: Intent) {
        startActivity(intent)
    }





}