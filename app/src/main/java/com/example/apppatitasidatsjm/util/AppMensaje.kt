package com.example.apppatitasidatsjm.util

import android.view.View
import androidx.core.content.ContextCompat
import com.example.apppatitasidatsjm.R
import com.google.android.material.snackbar.Snackbar

object AppMensaje {

    fun enviarMensaje(vista: View, mensaje: String, tipoMensaje: TipoMensaje){
        val snackBar = Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG)
        if(tipoMensaje == TipoMensaje.ERROR){
            snackBar.setBackgroundTint(
                ContextCompat.getColor(MiApp.instance,
                R.color.errorColor))
        }else {
            snackBar.setBackgroundTint(
                ContextCompat.getColor(MiApp.instance,
                    R.color.exitoColor)
            )
        }
        snackBar.show()
    }
}