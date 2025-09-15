package com.example.exp2_s5_katlheen_rodriguez.ui
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.exp2_s5_katlheen_rodriguez.model.User
import com.example.exp2_s5_katlheen_rodriguez.util.*
import androidx.compose.ui.graphics.Color
import com.example.exp2_s5_katlheen_rodriguez.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController, users: MutableList<User>) {

    //Estados para los campos de texto
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    //Contexto para mostrar los Toast
    val context = LocalContext.current

    //Estilo de texto
    val textStyle = TextStyle(color = textColor, fontFamily = FontFamily.SansSerif, fontSize = 14.sp)

    //Estructura principal
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro", color = textColor) },
                navigationIcon = {
                    //Botón de retroceso
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = textColor)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = backgroundColor)
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->

        //Contenedor principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .semantics { contentDescription = "Pantalla de registro" },
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

            //Campo de Usuario
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario", style = textStyle) },
                singleLine = true,
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = backgroundColor,
                    cursorColor = buttonRegisterColor,
                    focusedBorderColor = buttonRegisterColor,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = buttonRegisterColor,
                    unfocusedLabelColor = textColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Clave
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña", style = textStyle) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(), // Ocultar contraseña
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = backgroundColor,
                    cursorColor = buttonRegisterColor,
                    focusedBorderColor = buttonRegisterColor,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = buttonRegisterColor,
                    unfocusedLabelColor = textColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Confirmar Clave
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar Contraseña", style = textStyle) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = backgroundColor,
                    cursorColor = buttonRegisterColor,
                    focusedBorderColor = buttonRegisterColor,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = buttonRegisterColor,
                    unfocusedLabelColor = textColor
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            //Botón para Registrar
            Button(
                onClick = {
                    try {
                        //Validaciones de los campos
                        validateField(username, "El usuario no puede estar vacío") {}
                        validateField(password, "La contraseña no puede estar vacía") {}

                        when {
                            //Verificar si las claves no coinciden
                            password != confirmPassword -> throw Exception("Las contraseñas no coinciden")
                            //Verificar si el usuario ya existe
                            users.any { it.username == username } -> throw Exception("Usuario ya registrado")
                            //Verificar si ya hay más de 5 usuarios registrados
                            users.size >= 5 -> throw Exception("Máximo 5 usuarios permitidos")
                            else -> {
                                //Registro exitoso, se agrega el usuario
                                users.add(User(username, password))
                                Toast.makeText(context, "Usuario registrado: $username", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            }
                        }
                    } catch (e: Exception) {
                        //Manejo de errores
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonRegisterColor,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(60.dp)
            ) {
                Text("Registrar", style = textStyle)
            }
        }
    }
}