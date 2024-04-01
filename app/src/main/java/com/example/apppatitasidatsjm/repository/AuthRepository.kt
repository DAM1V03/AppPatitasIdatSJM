package com.example.apppatitasidatsjm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.apppatitasidatsjm.retrofit.PatitasCliente
import com.example.apppatitasidatsjm.retrofit.request.LoginRequest
import com.example.apppatitasidatsjm.retrofit.request.RegistroRequest
import com.example.apppatitasidatsjm.retrofit.response.LoginResponse
import com.example.apppatitasidatsjm.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var loginResponse = MutableLiveData<LoginResponse>()
    var registroResponse = MutableLiveData<RegistroResponse>()

    fun autenticarUsuario(loginRequest: LoginRequest): MutableLiveData<LoginResponse>
    {
        val call: Call<LoginResponse> = PatitasCliente.retrofitService.login(loginRequest)
        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(p0: Call<LoginResponse>, p1: Response<LoginResponse>) {
                loginResponse.value = p1.body()
            }
            override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {
                Log.e("ErrorLogin", p1.message.toString())
            }
        })
        return loginResponse
    }
    fun registrarUsuario(registroRequest: RegistroRequest): MutableLiveData<RegistroResponse>
    {
        val call: Call<RegistroResponse> = PatitasCliente.retrofitService.registro(registroRequest)
        call.enqueue(object : Callback<RegistroResponse>{
            override fun onResponse(p0: Call<RegistroResponse>, p1: Response<RegistroResponse>) {
                registroResponse.value = p1.body()
            }
            override fun onFailure(p0: Call<RegistroResponse>, p1: Throwable) {
                Log.e("ErrorRegistro", p1.message.toString())
            }
        })
        return registroResponse
    }
}