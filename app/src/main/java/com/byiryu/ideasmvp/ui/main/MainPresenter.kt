package com.byiryu.ideasmvp.ui.main

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.api.ApiService
import com.byiryu.ideasmvp.api.OnResultListener
import com.byiryu.ideasmvp.api.call
import com.byiryu.ideasmvp.data.Product
import com.byiryu.ideasmvp.data.Res
import com.byiryu.ideasmvp.ui.base.BasePresenter
import com.byiryu.ideasmvp.ui.detail.DetailActivity
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class MainPresenter<T : MainContract.View > @Inject constructor() : BasePresenter<T>(), MainContract.Presenter<T>, MainRecyclerAdapter.OnItemClickListener{

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var requestOptions: RequestOptions

    @Inject
    lateinit var requestManager: RequestManager

    lateinit var adapterView : MainRecyclerContract.View
    lateinit var adapterModel : MainRecyclerContract.Model

    var originItems : ArrayList<Product> = ArrayList()
    var addItems : ArrayList<Product> = ArrayList()

    lateinit var context: Context

    lateinit var handler: Handler



    var page : Int = 0

    override fun getProduct(context: Context, page : Int) {

        this.page = page
        this.context = context

        var call: Call<Res<Product>> = apiService.getProduct(page)

        call.call(true, context, object : OnResultListener<Res<Product>> {
            override fun onResult(
                isResult: Boolean,
                call: Call<Res<Product>>?,
                response: Response<Res<Product>>?,
                data: Res<Product>?
            ) {
                if(!isResult)
                    return
                else{
                    if (data != null) {
                        originItems.addAll(data.body!!)
                        addProduct()
                    }
                }
            }

        })
    }

    override fun addProduct() {

        adapterModel.clearItems()
        for(prod in originItems) {
            addItems.add(prod)
            if(addItems.size % 20 == 0)
                break
        }
        originItems.removeAll(addItems)
        adapterModel.addItems(addItems)
        adapterView.notifyAdapter()
        setLoading(false)

    }


    override fun LoadMore() {
        handler = Handler()
        handler.postDelayed({
            if(originItems.size < 20) {
                getProduct(context, page + 1)
            }else{
                addProduct()
            }
        }, 1500)

    }


    override fun setRecyclerView(mainRecyclerContract: MainRecyclerContract.View) {
        this.adapterView = mainRecyclerContract
        this.adapterView.setGlide(requestOptions, requestManager)
        this.adapterView.setOnClickListener(this)
    }

    override fun setRecyclerModel(mainRecyclerContract: MainRecyclerContract.Model) {
       this.adapterModel = mainRecyclerContract
    }


    override fun isLoading(): Boolean {
        return adapterView.getLoading()
    }
    override fun setLoading(loading: Boolean) {
       adapterView.setLoading(loading)
    }

    @SuppressLint("NewApi")
    override fun onItemClick(position: Int, imageView: ImageView) {
        var intent = Intent(context, DetailActivity::class.java )
        intent.putExtra("product", addItems[position])
        getMvpView().setTransition(imageView)
        getMvpView().goActivity(intent)
    }





}
