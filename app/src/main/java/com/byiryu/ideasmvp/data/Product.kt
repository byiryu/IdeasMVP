package com.byiryu.ideasmvp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product constructor(
    @SerializedName("thumbnail_520")
    var thumbnail : String? = null,
    var id : Int? = -1,
    var seller : String? = null,
    var title : String? = null
) : Serializable{

    override fun toString(): String {
        return "Product(thumbnail=$thumbnail, id=$id, seller=$seller, title=$title)"
    }


}