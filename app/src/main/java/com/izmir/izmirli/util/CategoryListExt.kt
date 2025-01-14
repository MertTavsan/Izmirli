package com.izmir.izmirli.util

import android.content.Context
import android.widget.Toast
import com.google.android.material.tabs.TabLayout

fun categoryList() : ArrayList<String> {
    var categoryList: ArrayList<String> = arrayListOf()
    with(categoryList){
        add("DEPREMLER API ÖDEV YER : BÜYÜKLÜK")
        add("TREN GARLARI ADI")
        add("ECZANE ADLARI")
        add("ECZANE ADRESLERİ")
        add("ECZANE TELEFONLARI")
        add("İSTASYON ADLARI")
        add("İSTASYON BOYLAMLARI")
        add("Hareketlilik")
        add("Yaşam")
        add("Enerji")
    }
    return categoryList
}
fun subCategoryList() : ArrayList<String>{
    var subCategoryList: ArrayList<String> = arrayListOf()
    with(subCategoryList){
        add("Taksi Durak")
        add("Askıda İzmir")
        add("Tren Garları")
        add("Metro istasyon")
    }
    return subCategoryList
}
fun subCategoryList2() : ArrayList<String>{
    var subCategoryList: ArrayList<String> = arrayListOf()
    with(subCategoryList){
        add("Taksi Durak2")
        add("Askıda İzmir2")
        add("Tren Garları2")
        add("Metro istasyon2")
    }
    return subCategoryList
}

fun TabLayout.autoFillList(subCategoryList: ArrayList<String>){
    subCategoryList.forEach {
        this.addTab(this.newTab().setText(it))
    }
}

fun Context.showMessage(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun TabLayout.onTabSelectedListener(
    onSelected: (TabLayout.Tab?) -> Unit,
    onReselected: (TabLayout.Tab?) -> Unit,
    onUnselected: (TabLayout.Tab?) -> Unit
) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            onSelected.invoke(tab)
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            onReselected.invoke(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            onUnselected.invoke(tab)
        }
    })
}