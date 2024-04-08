package com.example.apppatitasidatsjm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.apppatitasidatsjm.model.db.PatitasRoomDatabase
import com.example.apppatitasidatsjm.model.db.entity.PersonaEntity
import com.example.apppatitasidatsjm.repository.PersonaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonaViewModel(application: Application)
    : AndroidViewModel(application) {
    private val repository: PersonaRepository
    init {
        val personaDao = PatitasRoomDatabase.getDatabase(application).personaDao()
        repository = PersonaRepository(personaDao)
    }
    fun insertar(personaEntity: PersonaEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insertar(personaEntity)
    }
    fun actualizar(personaEntity: PersonaEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.actualizar(personaEntity)
    }
    fun eliminar() = viewModelScope.launch(Dispatchers.IO){
        repository.eliminar()
    }
    fun obtener():LiveData<PersonaEntity>{
        return  repository.obtener()
    }
}