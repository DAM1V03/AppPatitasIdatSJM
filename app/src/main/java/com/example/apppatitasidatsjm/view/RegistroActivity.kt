package com.example.apppatitasidatsjm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apppatitasidatsjm.R
import com.example.apppatitasidatsjm.databinding.ActivityMainBinding
import com.example.apppatitasidatsjm.databinding.ActivityRegistroBinding
import com.example.apppatitasidatsjm.retrofit.response.RegistroResponse
import com.example.apppatitasidatsjm.util.AppMensaje
import com.example.apppatitasidatsjm.util.TipoMensaje
import com.example.apppatitasidatsjm.viewmodel.AuthViewModel

class RegistroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        authViewModel.registroResponse.observe(this, Observer {
            response -> obtenerDatosRegistro(response!!)
        })
        binding.btnguardarusuario.setOnClickListener(this)
        binding.btnirlogin.setOnClickListener(this)

    }

    private fun obtenerDatosRegistro(response: RegistroResponse) {
        AppMensaje.enviarMensaje(binding.root,
            response.mensaje, TipoMensaje.SUCCESSFULL)
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnguardarusuario -> registrarUsuario()
            R.id.btnirlogin -> startActivity(
                Intent(applicationContext, MainActivity::class.java)
            )
        }
    }

    private fun registrarUsuario() {
        binding.btnguardarusuario.isEnabled = false
        binding.btnirlogin.isEnabled = false
        authViewModel.registrarUsuario(binding.etnombreregistro.text.toString(),
            binding.etapellidoregistro.text.toString(),
            binding.etemailregistro.text.toString(),
            binding.etcelularregistro.text.toString(),
            binding.etusuarioregistro.text.toString(),
            binding.etpasswordregistro.text.toString())
    }
}