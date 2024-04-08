package com.example.apppatitasidatsjm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppatitasidatsjm.R
import com.example.apppatitasidatsjm.databinding.FragmentMascotaBinding
import com.example.apppatitasidatsjm.view.adapters.MascotaAdapter
import com.example.apppatitasidatsjm.viewmodel.MascotaViewModel


class MascotaFragment : Fragment() {

    private var _binding: FragmentMascotaBinding? = null
    private val binding get() = _binding!!
    private lateinit var mascotaViewModel: MascotaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMascotaBinding.inflate(inflater, container, false)
        mascotaViewModel = ViewModelProvider(requireActivity())
            .get(MascotaViewModel::class.java)
        binding.rvmascota.layoutManager = LinearLayoutManager(requireActivity())
        listarMascotasPerdidas()
        return binding.root
    }

    private fun listarMascotasPerdidas() {
        mascotaViewModel.listarMascota().observe(viewLifecycleOwner,
            Observer {
                binding.rvmascota.adapter = MascotaAdapter(it)
            })
    }

}