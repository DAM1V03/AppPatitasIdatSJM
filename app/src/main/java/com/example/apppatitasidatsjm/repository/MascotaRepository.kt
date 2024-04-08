package com.example.apppatitasidatsjm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.apppatitasidatsjm.retrofit.PatitasCliente
import com.example.apppatitasidatsjm.retrofit.response.MascotaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaRepository {
    var mascotaResponse = MutableLiveData<List<MascotaResponse>>()
    fun listarMascotas(): MutableLiveData<List<MascotaResponse>>{
        val call: Call<List<MascotaResponse>> = PatitasCliente.retrofitService.mascotas()
        call.enqueue(object : Callback<List<MascotaResponse>>{
            override fun onResponse(
                p0: Call<List<MascotaResponse>>,
                p1: Response<List<MascotaResponse>>
            ) {
                mascotaResponse.value = p1.body()
            }

            override fun onFailure(p0: Call<List<MascotaResponse>>, p1: Throwable) {
                Log.e("ErrorListMascota", p1.message.toString())
            }
        })
        return mascotaResponse
    }
}