package com.izmir.izmirli.model

import com.google.gson.annotations.SerializedName

class IstasyonlarResponse : ArrayList<IstasyonlarResponse.IstasyonlarResponseItem>(){
    data class IstasyonlarResponseItem(
        @SerializedName("Boylam")
        var boylam: Double? = null,
        @SerializedName("IstasyonId")
        var istasyonId: Int? = null,
        @SerializedName("Enlem")
    var enlem: Double? = null,
    @SerializedName("IstasyonSirasi")
    var istasyonSirasi: Int? = null,
    @SerializedName("IstasyonAdi")
    var istasyonAdi: String? = null
    )
}