package com.kafein.weatherapp
import com.izmir.izmirli.model.*
import com.izmir.izmirli.model.DepremResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DepremAPI {
    companion object{
        const val DEPREMLER = "deprem/index.php?date=2020-01-01&limit=100"
    }

    @GET(DEPREMLER)
    fun getDepremler() : Single<DepremResponse>
}