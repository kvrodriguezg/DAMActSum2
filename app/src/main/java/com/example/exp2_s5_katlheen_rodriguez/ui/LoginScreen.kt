package com.example.exp2_s5_katlheen_rodriguez.ui
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.exp2_s5_katlheen_rodriguez.model.User
import com.example.exp2_s5_katlheen_rodriguez.util.*
import com.example.exp2_s5_katlheen_rodriguez.R
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, users: MutableList<User>) {
    //Usuario y Clave
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //Contexto para mostrar Toast
    val context = LocalContext.current

    //Estilo de texto
    val textStyle = TextStyle(color = textColor, fontFamily = FontFamily.SansSerif, fontSize = 14.sp)

    //Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .semantics { contentDescription = "Pantalla de inicio de sesión" },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Logo
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo de ChromaAssist",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Título
        Text(
            "ChromaAssist – Iniciar Sesión",
            style = textStyle.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        //Campo de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario", style = textStyle) },
            singleLine = true,
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonLoginColor,
                focusedBorderColor = buttonLoginColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonLoginColor,
                unfocusedLabelColor = textColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Campo de clave
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", style = textStyle) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(), //Para ocultar caracteres
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonLoginColor,
                focusedBorderColor = buttonLoginColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonLoginColor,
                unfocusedLabelColor = textColor
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Botón inicio de sesión
        Button(
            onClick = {
                try {
                    //Filter (función de orden superior)
                    val matchedUsers = users.filter { it.username == username }

                    if (matchedUsers.isEmpty()) {
                        throw Exception("Usuario no encontrado")
                    }

                    //Validar clave
                    val user = matchedUsers.first()
                    if (user.isPasswordValid(password)) {
                        //Si coincide la clave se redireccion a la pagina de bienvenida
                        navController.navigate("welcome/${user.username}")
                        return@Button
                    } else {
                        throw Exception("Contraseña incorrecta")
                    }
                } catch (e: Exception) {
                    //Mensaje de error
                    Toast.makeText(context, e.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonLoginColor, contentColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            //Ícono
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "Icono login",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ingresar", style = textStyle)
        }
        Spacer(modifier = Modifier.height(24.dp))
        //Fila con botones de navegación a Registro y Recuperar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //Botón para ir a registro
            Button(
                onClick = { navController.navigate("register") },
                colors = ButtonDefaults.buttonColors(containerColor = buttonRegisterColor, contentColor = Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f).height(60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.register),
                    contentDescription = "Icono de registrar usuario",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Registrarse", style = textStyle)
            }

            //Botón para ir a recuperar clave
            Button(
                onClick = { navController.navigate("recover") },
                colors = ButtonDefaults.buttonColors(containerColor = buttonRecoverColor, contentColor = Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f).height(60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reset),
                    contentDescription = "Icono de recuperar contraseña",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Recuperar", style = textStyle, maxLines = 1)
            }
        }
    }
}