package com.example.exp2_s5_katlheen_rodriguez.ui
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.exp2_s5_katlheen_rodriguez.util.*
import com.example.exp2_s5_katlheen_rodriguez.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavHostController, user: User) {
    val textStyle = TextStyle(color = textColor, fontFamily = FontFamily.SansSerif, fontSize = 18.sp)
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bienvenido", color = textColor) },
                actions = {
                    IconButton(onClick = {
                        //Al cerrar sesión se redirecciona al login
                        navController.navigate("login") {
                            Toast.makeText(context, "Has cerrado sesión.", Toast.LENGTH_LONG).show()
                            popUpTo("login") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar sesión", tint = textColor)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = backgroundColor)
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido ${user.initials}!",
                style = textStyle.copy(fontSize = 24.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}