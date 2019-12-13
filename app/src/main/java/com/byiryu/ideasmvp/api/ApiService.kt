package com.byiryu.ideasmvp.api

import com.byiryu.ideasmvp.data.Product
import com.byiryu.ideasmvp.data.ProductDetail
import com.byiryu.ideasmvp.data.Res

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/prod/products")
    fun getProduct(@Query("page") page: Int): Call<Res<Product>>

    @GET("/prod/products/{id}")
    fun getProductDetail(@Path("id") id : Int) : Call<Res<ProductDetail>>
}
