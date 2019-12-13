package com.byiryu.ideasmvp.ui.detail

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

import com.byiryu.ideasmvp.api.ApiService
import com.byiryu.ideasmvp.api.OnResultListener
import com.byiryu.ideasmvp.api.call
import com.byiryu.ideasmvp.data.Product
import com.byiryu.ideasmvp.data.ProductDetail
import com.byiryu.ideasmvp.data.Res
import com.byiryu.ideasmvp.ui.base.BasePresenter
import com.byiryu.ideasmvp.utils.CustomGlideRounded
import kotlinx.android.synthetic.main.activity_detail.*
import pyxis.uzuki.live.nyancat.NyanCat
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.sign


class DetailPresenter<T : DetailContract.View > @Inject constructor() : BasePresenter<  T>(), DetailContract.Presenter<T>{


    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var requestOptions: RequestOptions

    @Inject
    lateinit var requestManager: RequestManager

    lateinit var context: Context

    lateinit var adapterView: DetailRecyclerContract.View
    lateinit var adapterModel :  DetailRecyclerContract.Model

    lateinit var productDetail : ProductDetail


    override fun loadInit(context: Context, imageView: ImageView, url : String) {
        this.context = context
        requestOptions = requestOptions.transform(
            CenterCrop() , CustomGlideRounded(context,50, 0, CustomGlideRounded.CornerType.TOP)
        )
        requestManager.load(url)
            .apply(requestOptions)
            .into(imageView)

    }

    override fun loadProductDetail( id : Int) {

        adapterView.setGlide(requestOptions, requestManager)

        var call : Call<Res<ProductDetail>> = apiService.getProductDetail(id)

        call.call(true, context, object : OnResultListener<Res<ProductDetail>> {
            override fun onResult(
                isResult: Boolean,
                call: Call<Res<ProductDetail>>?,
                response: Response<Res<ProductDetail>>?,
                data: Res<ProductDetail>?
            ) {
                if(!isResult)
                    return
                else{
                    if (data != null) {
                        productDetail = data.body!![0]
                        adapterModel.addItems(productDetail.getImages())
                        adapterView.notifyAdapter()
                        getMvpView().setProgressSetting(productDetail.getImages().size)
                        getMvpView().initContents(productDetail)

                    }
                }
            }
        })
    }

    override fun setRecyclerView(adapter: DetailRecyclerContract.View) {
        adapterView = adapter
    }

    override fun setRecyclerModel(adapter: DetailRecyclerContract.Model) {
       adapterModel = adapter
    }




}