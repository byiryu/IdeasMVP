package com.byiryu.ideasmvp.ui.detail

import android.annotation.SuppressLint
import android.app.SharedElementCallback
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.*
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.byiryu.ideasmvp.R
import com.byiryu.ideasmvp.data.Product
import com.byiryu.ideasmvp.data.ProductDetail
import com.byiryu.ideasmvp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.view_detail_item.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailContract.View{


    @Inject
    lateinit var adapter: DetailRecyclerAdapter

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var detailPresenter: DetailContract.Presenter<DetailContract.View>

    lateinit var snapHelper: SnapHelper

    lateinit var product: Product

    var pageIndex = 1

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var requestOptions : RequestOptions



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        activityComponent.inject(this)

        detailPresenter.onAttach(this)

        init()

    }


    @SuppressLint("NewApi")
    override fun init(){

        with(window){
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.parseColor("#000000")
        }

        product = intent.getSerializableExtra("product") as Product

        product_img.transitionName = product.id.toString()

        detailPresenter.loadInit(this, product_img, product.thumbnail!!)

        detailPresenter.setRecyclerModel(adapter)
        detailPresenter.setRecyclerView(adapter)

        snapHelper = PagerSnapHelper()
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        snapHelper.attachToRecyclerView(recyclerView)

        Handler().postDelayed({
            detailPresenter.loadProductDetail(  product.id!! )
        },500)
        transAnim(true)


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                viewPagerIndicator.progress = layoutManager.findFirstVisibleItemPosition()+1
            }
        })


        btn_back.setOnClickListener {
           finish()
        }
    }


    override fun initContents(productDetail: ProductDetail) {

        detail_warning.text = detail_warning.text.toString().replace(" ", "\u00A0");
        detail_contents.visibility = View.VISIBLE
        detail_images.visibility = View.VISIBLE
        detail_seller.text = productDetail.seller
        detail_title.text = productDetail.title

        if(productDetail.discount_cost == null){
            detail_discount_cost.visibility = View.GONE
        }
        else{
            detail_discount_cost.text = productDetail.discount_cost
            detail_cost.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        if(productDetail.discount_rate == null)
            detail_discount_rate.visibility = View.GONE
        else
            detail_discount_rate.text = "-"+productDetail.discount_rate

        detail_desc.text = productDetail.discription
        detail_cost.text = productDetail.cost

    }

    override fun setProgressSetting(max : Int) {
        viewPagerIndicator.max = max
        viewPagerIndicator.progress = pageIndex

    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.onDetach()
    }

    override fun onBackPressed() {
       finish()
    }

    private fun transAnim(bool : Boolean){
        var aniInSet = AnimationSet(true)
        aniInSet.interpolator = AccelerateInterpolator()
        var transIn = TranslateAnimation(0f,0f,300.0f, 0f)
        transIn.duration = 300

        aniInSet.addAnimation(transIn)
        aniInSet.startOffset = 500

        if (bool){
            btn_buy.animation = aniInSet
            btn_buy.visibility = View.VISIBLE
        }

    }

}