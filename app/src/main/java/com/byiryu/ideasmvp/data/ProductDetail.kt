package com.byiryu.ideasmvp.data

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class ProductDetail {

    @SerializedName("id")
    var id : Int? = -1

    @SerializedName("thumbnail_720")
    var thumbnail720 : String? = null

    @SerializedName("thumbnail_list_320")
    var thumbnail320 : String? = null

    @SerializedName("title")
    var title : String? = null

    @SerializedName("seller")
    var seller : String? = null

    @SerializedName("cost")
    var cost : String? = null

    @SerializedName("discount_cost")
    var discount_cost : String? = null

    @SerializedName("discount_rate")
    var discount_rate : String? = null

    @SerializedName("description")
    var discription : String? = null

    override fun toString(): String {
        return "ProductDetail(id=$id, thumbnail720=$thumbnail720, thumbnail320=$thumbnail320, title=$title, seller=$seller, cost=$cost, discount_cost=$discount_cost, discount_rate=$discount_rate, discription=$discription)"
    }

    fun getImages() : ArrayList<Images>{
        var item = ArrayList<Images>()

        for(img in thumbnail320!!.split("#"))
            item.add(Images(Random().nextInt(100000), img))

        return item
    }

    class Images constructor(var id : Int, var url : String)


}
