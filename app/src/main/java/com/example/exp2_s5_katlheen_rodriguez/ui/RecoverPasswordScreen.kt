package com.example.exp2_s5_katlheen_rodriguez.ui
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.exp2_s5_katlheen_rodriguez.model.User
import com.example.exp2_s5_katlheen_rodriguez.util.*
import com.example.exp2_s5_katlheen_rodriguez.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoverPasswordScreen(navController: NavHostController, users: MutableList<User>) {
    //Usuario
    var username by remember { mutableStateOf("") }

    //Contexto para Toasts
    val context = LocalContext.current

    //Estilo de texto
    val textStyle = TextStyle(color = textColor, fontFamily = FontFamily.SansSerif, fontSize = 14.sp)

    //Estructura principal
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recuperar Contraseña", color = textColor) }, //Título en la barra
                navigationIcon = {
                    //Botón para volver atrás
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = textColor)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = backgroundColor)
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        //Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .semantics { contentDescription = "Pantalla recuperar contraseña" },
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            //Logo
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Logo de ChromaAssist",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Campo para ingresar el usuario
            OutlinedTextField(
                value = username,
                onValueChange = { username = it }, //Actualiza el estado
                label = { Text("Usuario", style = textStyle) },
                singleLine = true,
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = backgroundColor,
                    cursorColor = buttonRecoverColor,
                    focusedBorderColor = buttonRecoverColor,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = buttonRecoverColor,
                    unfocusedLabelColor = textColor
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            //Botón para enviar recuperación
            Button(
                onClick = {
                    //Busca el usuario en la lista
                    val user = users.find { it.username == username }

                    //Si existe muestra la contraseña, sino muestra error
                    if (user != null) {
                        Toast.makeText(context, "Contraseña: ${user.password}", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonRecoverColor,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                //Ícono del botón
                Image(
                    painter = painterResource(id = R.drawable.reset),
                    contentDescription = "Icono de enviar recuperación",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Enviar", style = textStyle)
            }
        }
    }
}