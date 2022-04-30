package com.izmir.izmirli

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.izmir.izmirli.adapter.IzmirAdapter
import com.izmir.izmirli.databinding.ActivityMainBinding
import com.izmir.izmirli.model.*
import com.izmir.izmirli.service.DepremAPIService
import com.izmir.izmirli.util.*
import com.kafein.weatherapp.IzmirAPIService
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.logging.Handler

class MainActivity : AppCompatActivity(), Example {

    private val izmirAdapter = IzmirAdapter()
    private lateinit var binding: ActivityMainBinding
    private lateinit var exampleInterface: Example
    private var izmirAPIService = IzmirAPIService()


    private var eczaneAdlari :ArrayList<String> = arrayListOf()
    private var eczaneAdresleri :ArrayList<String> = arrayListOf()
    private var eczaneTelefonlari :ArrayList<String> = arrayListOf()

    private var istasyonAdlari :ArrayList<String> = arrayListOf()
    private var istasyonBoylamlari :ArrayList<String> = arrayListOf()

    private var trenGarlariAdi :ArrayList<String> = arrayListOf()

    private var depremAPIService:DepremAPIService= DepremAPIService()
    private var depremSiddeti:ArrayList<String> = arrayListOf()
    private var depremResponseResult: ArrayList<DepremResponse.Result> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pbLoader.visibility = View.VISIBLE
        exampleInterface = this

        categoryList().forEach {
            binding.tbCategory.addTab(binding.tbCategory.newTab().setText(it))
        }

        izmirAPIService.getNobetciEczane()
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(object : DisposableSingleObserver<NobetciEczaneResponse>() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(eczaneler: NobetciEczaneResponse) {
                    runOnUiThread {
                        eczaneler[0].tarih?.replace("T08:00:00", " ")?.trim()?.let {
                            Log.i("Tarih",
                                it
                            )
                        }
                        for (i in eczaneler){
                            i.adi?.let {
                                eczaneAdlari.add(it)
                            }
                        }

                        for (i in eczaneler){
                            i.adres?.let {
                                eczaneAdresleri.add(it)
                            }
                        }

                        for (i in eczaneler){
                            i.telefon?.let {
                                eczaneTelefonlari.add(it)
                            }
                        }


                        //filter fun -> .filter { it.bolgeId == 19 } as ArrayList<NobetciEczaneResponse.NobetciEczaneResponseItem>
                        izmirAdapter.setIzmirData(eczaneler)
                        Log.i("response", eczaneler.toString())
                        binding.pbLoader.visibility = View.GONE
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.i("response", e.message.toString())
                }
            })

        izmirAPIService.getIstasyon()
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(object : DisposableSingleObserver<IstasyonlarResponse>() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(istasyonlar: IstasyonlarResponse) {
                    runOnUiThread {

                        for (i in istasyonlar){
                            i.istasyonAdi?.let {
                                istasyonAdlari.add(it)
                            }
                        }

                        for (i in istasyonlar){
                            i.boylam?.let {
                                istasyonBoylamlari.add(it.toString())
                            }
                        }


                        //filter fun -> .filter { it.bolgeId == 19 } as ArrayList<NobetciEczaneResponse.NobetciEczaneResponseItem>
                        //izmirAdapter.setIzmirData(istasyonlar)
                        Log.i("response", istasyonlar.toString())
                        binding.pbLoader.visibility = View.GONE
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.i("response", e.message.toString())
                }
            })

        izmirAPIService.getTrenGarlari()
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(object : DisposableSingleObserver<TrenGarlariResponse>() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(trenGarlariResponse: TrenGarlariResponse) {
                    runOnUiThread {

                        Log.i("response1", trenGarlariResponse.toString())
                        //trenGarlariResponse.onemliyer?.get(0)?.aDI?.let { trenGarlariAdi.add(it) }

                        trenGarlariResponse.onemliyer?.let {
                            for (i in it){
                                i?.aDI?.let {
                                    trenGarlariAdi.add(it)
                                }
                            }
                        }

                        //filter fun -> .filter { it.bolgeId == 19 } as ArrayList<NobetciEczaneResponse.NobetciEczaneResponseItem>
                        //izmirAdapter.setIzmirData(istasyonlar)
                        Log.i("response1", trenGarlariResponse.toString())
                        binding.pbLoader.visibility = View.GONE
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.i("responseError", e.message.toString())
                }
            })

        depremAPIService.getDepremler()
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(object : DisposableSingleObserver<DepremResponse>() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(depremResponse: DepremResponse) {
                    runOnUiThread {

                        Log.i("responseDeprem", depremResponse.toString())
                        //trenGarlariResponse.onemliyer?.get(0)?.aDI?.let { trenGarlariAdi.add(it) }

                        depremResponse.result?.let {

                            for (i in it){
                               /* i?.let {
                                    depremResponseResult.add(it)
                                }*/

                                i?.lokasyon?.let {
                                   var m=it
                                    i?.mag?.let {
                                        if(it>2.5){
                                            m=m+" : "+it.toString()
                                            depremSiddeti.add(m)
                                        }
                                    }

                                }
                            }

                        }



                        //filter fun -> .filter { it.bolgeId == 19 } as ArrayList<NobetciEczaneResponse.NobetciEczaneResponseItem>
                        //izmirAdapter.setIzmirData(istasyonlar)
                        //Log.i("response1", trenGarlariResponse.toString())
                        binding.pbLoader.visibility = View.GONE
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.i("responseError", e.message.toString())
                }
            })


        binding.apply {
            izmirAdapter.eczaneClickListener = {
                this@MainActivity.eczaneDetay(it)
            }

            rvMultitypeList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rvMultitypeList.adapter = izmirAdapter

            categoryList().forEach {

            }

            tbCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tbSubCategory.removeAllTabs()
                    when(tab?.position){

                        0 -> { tbSubCategory.autoFillList(depremSiddeti) }
                        1 -> { tbSubCategory.autoFillList(trenGarlariAdi) }
                        2 -> {tbSubCategory.autoFillList(eczaneAdlari)}
                        3 -> {tbSubCategory.autoFillList(eczaneAdresleri)}
                        4 -> {tbSubCategory.autoFillList(eczaneTelefonlari)}
                        5 -> {tbSubCategory.autoFillList(istasyonAdlari)}
                        6 -> {tbSubCategory.autoFillList(istasyonBoylamlari)}
                        7-> {tbSubCategory.autoFillList(subCategoryList())}
                        8-> {tbSubCategory.autoFillList(subCategoryList2())}


                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

            tbSubCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        //0 -> { izmirAdapter.adapterFill(subCategoryList()) }
                       // 1 -> { izmirAdapter.adapterFill(subCategoryList2()) }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

        }
    }

    override fun listeyiDoldur(liste: ArrayList<String>) {

    }



}