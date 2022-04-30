package com.izmir.izmirli.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class DepremResponse {
    @SerializedName("status")
    var status : Boolean? = null
    @SerializedName("desc")
    var desc   : String? = null
    @SerializedName("result")
    var result : ArrayList<Result?>? = null

    data class Result (

        @SerializedName("_id")
        var Id          : Id?               = Id(),
        @SerializedName("mag")
        var mag         : Double?           = null,
        @SerializedName("lng") var lng         : Double?           = null,
        @SerializedName("lat") var lat         : Double?           = null,
        @SerializedName("lokasyon") var lokasyon    : String?           = null,
        @SerializedName("depth") var depth       : Double?           = null,
        @SerializedName("coordinates") var coordinates : ArrayList<Double> = arrayListOf(),
        @SerializedName("title") var title       : String?           = null,
        @SerializedName("rev") var rev         : String?           = null,
        @SerializedName("timestamp"   ) var timestamp   : Int?              = null,
        @SerializedName("date_stamp"  ) var dateStamp   : String?           = null,
        @SerializedName("date"        ) var date        : String?           = null,
        @SerializedName("hash"        ) var hash        : String?           = null,
        @SerializedName("hash2"       ) var hash2       : String?           = null

    )
    data class Id (

        @SerializedName("\$oid" ) var oid : String? = null

    )
}