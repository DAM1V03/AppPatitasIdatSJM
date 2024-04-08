package com.example.apppatitasidatsjm.util

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesManager() {
    private val APP_SETTINGS_FILE = "APP_SETTING";
    private fun getSharedPreferences():SharedPreferences{
        return MiApp.applicationContext.getSharedPreferences(APP_SETTINGS_FILE,
            MODE_PRIVATE)
    }
    fun setSomeBooleanValue(nombre: String, valor:Boolean){
        val editor = getSharedPreferences().edit()
        editor.putBoolean(nombre, valor)
        editor.commit()
    }
    fun getSomeBooleanValue(nombre: String):Boolean{
        return getSharedPreferences().getBoolean(nombre, false)
    }
    fun setSomeStringValue(nombre: String, valor:String){
        val editor = getSharedPreferences().edit()
        editor.putString(nombre, valor)
        editor.commit()
    }
    fun getSomeStringValue(nombre: String):String{
        return getSharedPreferences().getString(nombre, "").toString()
    }
    fun deletePreference(nombre: String){
        getSharedPreferences().edit().remove(nombre).apply()
    }
}

