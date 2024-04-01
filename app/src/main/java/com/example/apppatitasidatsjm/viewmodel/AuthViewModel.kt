package com.example.apppatitasidatsjm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apppatitasidatsjm.repository.AuthRepository
import com.example.apppatitasidatsjm.retrofit.request.LoginRequest
import com.example.apppatitasidatsjm.retrofit.request.RegistroRequest
import com.example.apppatitasidatsjm.retrofit.response.LoginResponse
import com.example.apppatitasidatsjm.retrofit.response.RegistroResponse

class AuthViewModel: ViewModel() {
    var loginResponse: LiveData<LoginResponse>
    var registroResponse: LiveData<RegistroResponse>
    private var repository = AuthRepository()
    init {
        loginResponse = repository.loginResponse
        registroResponse = repository.registroResponse
    }
    fun autenticarUsuario(usuario:String, password:String){
        loginResponse = repository.autenticarUsuario(
            LoginRequest(usuario, password))
    }
    fun registrarUsuario(nombre:String, apellido:String, email:String, celular:String,
                         usuario:String, password:String){
        registroResponse = repository.registrarUsuario(
            RegistroRequest(nombre,apellido,email,celular,usuario,password)
        )
    }
}