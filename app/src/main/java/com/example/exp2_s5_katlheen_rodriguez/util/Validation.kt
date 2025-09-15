package com.example.exp2_s5_katlheen_rodriguez.util

import com.example.exp2_s5_katlheen_rodriguez.model.User

fun validateUserLogin(username: String, password: String, users: List<User>): Boolean {
    val user = users.find { it.username == username }
    return user?.password == password
}

//Funcion para validar dependiendo del caso
fun validateRegistration(
    username: String, password: String, confirmPassword: String, users: List<User>
): String? {
    return when {
        username.isBlank() || password.isBlank() -> "Complete todos los campos"
        password != confirmPassword -> "Contraseñas no coinciden"
        users.any { it.username == username } -> "Usuario ya registrado"
        users.size >= 5 -> "Máximo 5 usuarios permitidos"
        else -> null
    }
}