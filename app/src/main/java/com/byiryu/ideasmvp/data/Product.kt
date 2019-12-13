package com.byiryu.ideasmvp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product : Serializable{

    @SerializedName("thumbnail_520")
    var thumbnail : String? = null

    @SerializedName("id")
    var id : Int? = -1

    @SerializedName("seller")
    var seller : String? = null

    @SerializedName("title")
    var title : String? = null

    override fun toString(): String {
        return "Product(thumbnail=$thumbnail, id=$id, seller=$seller, title=$title)"
    }


}