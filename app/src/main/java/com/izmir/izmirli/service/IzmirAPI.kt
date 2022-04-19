package com.kafein.weatherapp

import com.izmir.izmirli.model.GameTypeResponse
import com.izmir.izmirli.model.IstasyonlarResponse
import com.izmir.izmirli.model.NobetciEczaneResponse
import com.izmir.izmirli.model.TrenGarlariResponse
import io.reactivex.Single
import retrofit2.http.GET

interface IzmirAPI {

    //https://openapi.izmir.bel.tr/api/ibb/cbs/trengarlari
    // BASEurl -> https://openapi.izmir.bel.tr/api/ibb/
    // EXTurl -> cbs/trengarlari
    companion object{
        const val TREN_GARLARI = "cbs/trengarlari"
        const val GAME_TYPE = "gameType"
        const val NOBETCI_ECZANE = "ibb/nobetcieczaneler"
        const val ISTASYONLAR = "izban/istasyonlar"
    }

    @GET(TREN_GARLARI)
    fun getTrenGarlari() : Single<TrenGarlariResponse>

    @GET(GAME_TYPE)
    fun getGameType() : Single<GameTypeResponse>

    @GET(NOBETCI_ECZANE)
    fun getNobetciEczane() : Single<NobetciEczaneResponse>

    @GET(ISTASYONLAR)
    fun getIstasyon() : Single<IstasyonlarResponse>
}