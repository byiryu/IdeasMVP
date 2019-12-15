package com.byiryu.ideasmvp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class ProductDetail constructor(
    var id : Int? = -1,

    @SerializedName("thumbnail_720")
    var thumbnail720 : String? = null,

    @SerializedName("thumbnail_list_320")
    var thumbnail320 : String? = null,

    var title : String? = null,

    var seller : String? = null,

    var cost : String? = null,

    var discount_cost : String? = null,

    var discount_rate : String? = null,

    var description : String? = null
) : Serializable {



    fun getImages() : ArrayList<Images>{
        var item = ArrayList<Images>()

        for(img in thumbnail320!!.split("#"))
            item.add(Images(Random().nextInt(100000), img))

        return item
    }

    override fun toString(): String {
        return "ProductDetail(id=$id, thumbnail720=$thumbnail720, thumbnail320=$thumbnail320, title=$title, seller=$seller, cost=$cost, discount_cost=$discount_cost, discount_rate=$discount_rate, description=$description)"
    }

    class Images constructor(var id : Int, var url : String)


}
