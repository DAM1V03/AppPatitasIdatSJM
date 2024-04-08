package com.example.apppatitasidatsjm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apppatitasidatsjm.repository.MascotaRepository
import com.example.apppatitasidatsjm.retrofit.response.MascotaResponse

class MascotaViewModel: ViewModel() {
    private var repository = MascotaRepository()
    fun listarMascota(): LiveData<List<MascotaResponse>> {
        return repository.listarMascotas()
    }
}