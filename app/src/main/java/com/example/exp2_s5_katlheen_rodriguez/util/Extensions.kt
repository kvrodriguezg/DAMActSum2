package com.example.exp2_s5_katlheen_rodriguez.util
import com.example.exp2_s5_katlheen_rodriguez.model.User

//Función de extensión para validar contraseña
fun User.isPasswordValid(input: String): Boolean = this.password == input

//Propiedad de extensión para iniciales
val User.initials: String
    get() = username.take(2).uppercase()

//Función inline de validación
inline fun validateField(value: String, message: String, onValid: () -> Unit) {
    if (value.isBlank()) throw Exception(message)
    else onValid()
}