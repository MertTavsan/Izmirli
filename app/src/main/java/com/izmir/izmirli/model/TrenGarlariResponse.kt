package com.izmir.izmirli.model


import com.google.gson.annotations.SerializedName
import java.util.ArrayList


class TrenGarlariResponse{
    @SerializedName("kayit_sayisi")
    var kayitSayisi: Int? = null

    @SerializedName("sayfa_numarasi")
    var sayfaNumarasi: Int? = null
    @SerializedName("sayfadaki_kayitsayisi")
    var sayfadakiKayitsayisi: Int? = null
    @SerializedName("toplam_sayfa_sayisi")
    var toplamSayfaSayisi: Int? = null
    @SerializedName("onemliyer")
    var onemliyer: ArrayList<Onemliyer?>?=null
/*
    class OnemliyerResponse : ArrayList<OnemliyerResponse.OnemliyerResponseItem>(){

        data class OnemliyerResponseItem(
        @SerializedName("ACIKLAMA")
        var aCIKLAMA: String? = null,
        @SerializedName("ADI")
        var aDI: String? = null,
        @SerializedName("BOYLAM")
        var bOYLAM: Double? = null,
        @SerializedName("ENLEM")
        var eNLEM: Double? = null,
        @SerializedName("ILCE")
        var iLCE: String? = null,
        @SerializedName("ILCEID")
        var iLCEID: String? = null,
        @SerializedName("KAPINO")
        var kAPINO: String? = null,
        @SerializedName("MAHALLE")
        var mAHALLE: String? = null,
        @SerializedName("MAHALLEID")
        var mAHALLEID: Any? = null,
        @SerializedName("YOL")
        var yOL: String? = null
    )}*/
data class Onemliyer(
    @SerializedName("ACIKLAMA")
    var aCIKLAMA: String? = null,
    @SerializedName("ADI")
    var aDI: String? = null,
    @SerializedName("BOYLAM")
    var bOYLAM: Double? = null,
    @SerializedName("ENLEM")
    var eNLEM: Double? = null,
    @SerializedName("ILCE")
    var iLCE: String? = null,
    @SerializedName("ILCEID")
    var iLCEID: String? = null,
    @SerializedName("KAPINO")
    var kAPINO: String? = null,
    @SerializedName("MAHALLE")
    var mAHALLE: String? = null,
    @SerializedName("MAHALLEID")
    var mAHALLEID: Any? = null,
    @SerializedName("YOL")
    var yOL: String? = null
)
}