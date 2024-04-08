package com.example.apppatitasidatsjm.repository

import androidx.lifecycle.LiveData
import com.example.apppatitasidatsjm.model.db.dao.PersonaDao
import com.example.apppatitasidatsjm.model.db.entity.PersonaEntity

class PersonaRepository(private val personaDao: PersonaDao) {
    suspend fun insertar(personaEntity: PersonaEntity){
        personaDao.insertar(personaEntity)
    }
    suspend fun actualizar(personaEntity: PersonaEntity){
        personaDao.actualizar(personaEntity)
    }
    suspend fun eliminar(){
        personaDao.eliminarTodo()
    }
    fun obtener():LiveData<PersonaEntity>{
        return personaDao.obtener()
    }
}