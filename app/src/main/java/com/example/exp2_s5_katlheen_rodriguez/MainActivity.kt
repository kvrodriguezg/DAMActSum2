package com.example.exp2_s5_katlheen_rodriguez
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.exp2_s5_katlheen_rodriguez.model.User
import com.example.exp2_s5_katlheen_rodriguez.ui.LoginScreen
import com.example.exp2_s5_katlheen_rodriguez.ui.RegisterScreen
import com.example.exp2_s5_katlheen_rodriguez.ui.RecoverPasswordScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainApp() }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    //Usuarios ya registrados
    val preRegistered = listOf(
        User("usuario1", "clave1"),
        User("usuario2", "clave2"),
        User("usuario3", "clave3")
    )

    //Se agregan usuarios
    val users = remember { mutableStateListOf<User>().apply { addAll(preRegistered) } }

    //Navegación
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, users) }
        composable("register") { RegisterScreen(navController, users) }
        composable("recover") { RecoverPasswordScreen(navController, users) }
    }
}