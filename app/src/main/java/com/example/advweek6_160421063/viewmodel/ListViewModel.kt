package com.example.advweek6_160421063.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek6_160421063.model.Brand
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application):AndroidViewModel(application)
{
    val brandsLD = MutableLiveData<ArrayList<Brand>>()
    val brandLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private  var queue: RequestQueue? = null

    fun refresh(){
        Log.d("CEKMASUK", "masukVolley")
        loadingLD.value = true
        brandLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/brands/brands.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<List<Brand>>() { }.type
                val result = Gson().fromJson<List<Brand>>(it, sType)
                brandsLD.value = result as ArrayList<Brand>?
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                brandLoadErrorLD.value = false
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}