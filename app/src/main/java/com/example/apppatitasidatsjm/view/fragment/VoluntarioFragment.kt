package com.example.apppatitasidatsjm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.apppatitasidatsjm.R
import com.example.apppatitasidatsjm.databinding.FragmentVoluntarioBinding
import com.example.apppatitasidatsjm.model.db.entity.PersonaEntity
import com.example.apppatitasidatsjm.retrofit.response.RegistroResponse
import com.example.apppatitasidatsjm.util.AppMensaje
import com.example.apppatitasidatsjm.util.TipoMensaje
import com.example.apppatitasidatsjm.viewmodel.PersonaViewModel
import com.example.apppatitasidatsjm.viewmodel.VoluntarioViewModel

class VoluntarioFragment : Fragment(), View.OnClickListener {
    var _binding: FragmentVoluntarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var voluntarioViewModel: VoluntarioViewModel
    private lateinit var personaViewModel: PersonaViewModel
    private lateinit var personaEntity: PersonaEntity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoluntarioBinding.inflate(inflater, container, false)
        voluntarioViewModel = ViewModelProvider(requireActivity())
            .get(VoluntarioViewModel::class.java)
        personaViewModel = ViewModelProvider(requireActivity())
            .get(PersonaViewModel::class.java)
        personaViewModel.obtener()
            .observe(viewLifecycleOwner, Observer {
                persona ->
                persona?.let {
                    if(persona.esvoluntario == "1"){
                        formVoluntario()
                    }else{
                        personaEntity = persona
                    }
                }
            })
        binding.btnregistrarvoluntario.setOnClickListener(this)
        voluntarioViewModel.registroResponse.observe(viewLifecycleOwner,
            Observer {
                respuestaRegistroVoluntario(it)
            })
        return binding.root
    }

    private fun respuestaRegistroVoluntario(it: RegistroResponse) {
        if(it.rpta){
            val nuevaPersonaEntity = PersonaEntity(
                personaEntity.id, personaEntity.nombres, personaEntity.apellidos,
                personaEntity.email, personaEntity.celular, personaEntity.usuario,
                personaEntity.password, "1"
            )
            personaViewModel.actualizar(nuevaPersonaEntity)
            formVoluntario()
        }
        AppMensaje.enviarMensaje(binding.root, it.mensaje, TipoMensaje.SUCCESSFULL)
        binding.btnregistrarvoluntario.isEnabled = true
    }

    override fun onClick(p0: View?) {
        if(binding.cbaceptarterminos.isChecked){
            binding.btnregistrarvoluntario.isEnabled = false
            voluntarioViewModel.registrarVoluntario(personaEntity.id)
        }else{
            AppMensaje.enviarMensaje(binding.root,
                "Acepte los términos y condiciones para ser voluntario",
                TipoMensaje.ERROR)
        }
    }

    private fun formVoluntario(){
        binding.cbaceptarterminos.visibility = View.GONE
        binding.btnregistrarvoluntario.visibility = View.GONE
        binding.textView4.visibility = View.GONE
        binding.textView3.text = "Gracias por su compromiso como voluntario"
    }

}