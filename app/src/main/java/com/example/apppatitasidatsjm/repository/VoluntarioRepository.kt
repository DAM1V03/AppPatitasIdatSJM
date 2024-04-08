package com.example.apppatitasidatsjm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.apppatitasidatsjm.retrofit.PatitasCliente
import com.example.apppatitasidatsjm.retrofit.request.VoluntarioRequest
import com.example.apppatitasidatsjm.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoluntarioRepository {
    var registroResponse = MutableLiveData<RegistroResponse>()

    fun registraVoluntario(idPersona: Int): MutableLiveData<RegistroResponse>{
        val call: Call<RegistroResponse> = PatitasCliente.retrofitService.voluntario(
            VoluntarioRequest(idPersona)
        )
        call.enqueue(object : Callback<RegistroResponse>{
            override fun onResponse(p0: Call<RegistroResponse>, p1: Response<RegistroResponse>) {
                registroResponse.value = p1.body()
            }
            override fun onFailure(p0: Call<RegistroResponse>, p1: Throwable) {
                Log.e("ErrorRegVolun", p1.message.toString())
            }

        })
        return registroResponse
    }
}