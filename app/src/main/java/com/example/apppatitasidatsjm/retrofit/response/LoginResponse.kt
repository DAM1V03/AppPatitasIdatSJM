package com.example.apppatitasidatsjm.retrofit.response

data class LoginResponse (
    var rpta: Boolean,
    var idpersona: String,
    var nombres: String,
    var apellidos: String,
    var email: String,
    var celular: String,
    var usuario: String,
    var password: String,
    var esvoluntario: String,
    var mensaje: String
)