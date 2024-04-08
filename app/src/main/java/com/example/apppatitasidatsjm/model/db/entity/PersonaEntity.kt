package com.example.apppatitasidatsjm.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persona")
data class PersonaEntity (
    @PrimaryKey
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val celular: String,
    val usuario: String,
    val password: String,
    val esvoluntario: String,
)