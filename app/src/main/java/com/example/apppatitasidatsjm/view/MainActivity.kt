package com.example.apppatitasidatsjm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apppatitasidatsjm.R
import com.example.apppatitasidatsjm.databinding.ActivityMainBinding
import com.example.apppatitasidatsjm.model.db.entity.PersonaEntity
import com.example.apppatitasidatsjm.retrofit.response.LoginResponse
import com.example.apppatitasidatsjm.util.AppMensaje
import com.example.apppatitasidatsjm.util.SharedPreferencesManager
import com.example.apppatitasidatsjm.util.TipoMensaje
import com.example.apppatitasidatsjm.viewmodel.AuthViewModel
import com.example.apppatitasidatsjm.viewmodel.PersonaViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)
        authViewModel.loginResponse.observe(this, Observer {
            response -> obtenerDatosLogin(response!!)
        })
        if(recordarDatosLogin()){
            binding.cbrecordar.isChecked = true
            binding.etusuario.isEnabled = false
            binding.etpassword.isEnabled = false
            binding.cbrecordar.text = "Quitar check para ingresar con otro usuario"
            personaViewModel.obtener()
                .observe(this, Observer { persona ->
                    persona?.let {
                        binding.etusuario.setText(persona.usuario)
                        binding.etpassword.setText(persona.password)
                    }
                })
        }else{
            personaViewModel.eliminar()
        }
        binding.btningresar.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)
        binding.cbrecordar.setOnClickListener(this)
    }
    private fun obtenerDatosLogin(response: LoginResponse) {
        if(response.rpta){
            val personaEntity = PersonaEntity(
                response.idpersona.toInt(), response.nombres, response.apellidos,
                response.email, response.celular, response.usuario, response.password,
                response.esvoluntario
            )
            if(recordarDatosLogin()){
                personaViewModel.actualizar(personaEntity)
            }else{
                personaViewModel.insertar(personaEntity)
                if(binding.cbrecordar.isChecked){
                    SharedPreferencesManager().setSomeBooleanValue("PREF_RECORDAR",
                        true)
                }
            }

            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }else{
            AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ERROR)
        }
        binding.btningresar.isEnabled = true
        binding.btnregistrar.isEnabled = true
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btningresar -> autenticarUsuario()
            R.id.btnregistrar -> startActivity(Intent(applicationContext,
                RegistroActivity::class.java))
            R.id.cbrecordar -> setearValoresRecordar(vista)
        }
    }

    private fun setearValoresRecordar(vista: View) {
        if(vista is CheckBox){
            val checkeo = vista.isChecked
            if(!checkeo){
                if(recordarDatosLogin()){
                    SharedPreferencesManager().deletePreference("PREF_RECORDAR")
                    personaViewModel.eliminar()
                    binding.etusuario.isEnabled = true
                    binding.etpassword.isEnabled = true
                    binding.cbrecordar.text = getString(R.string.valcbrecordar)
                }
            }
        }
    }

    private fun autenticarUsuario() {
        binding.btningresar.isEnabled = false
        binding.btnregistrar.isEnabled = false
        authViewModel.autenticarUsuario(binding.etusuario.text.toString(),
            binding.etpassword.text.toString())
    }
    private fun recordarDatosLogin(): Boolean{
        return SharedPreferencesManager().getSomeBooleanValue("PREF_RECORDAR")
    }
}