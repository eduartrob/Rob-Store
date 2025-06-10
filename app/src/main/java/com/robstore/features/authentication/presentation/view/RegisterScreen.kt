package com.robstore.features.authentication.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.robstore.R

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,

) {
    var username by remember { mutableStateOf("") }


    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var hasFocus by remember { mutableStateOf(false) }
    var wasFocusedOnce by remember { mutableStateOf(false) }

    // Función de validación
    fun validateEmail(input: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    // Color animado del borde
    val borderColor by animateColorAsState(
        targetValue = if (!isEmailValid && wasFocusedOnce) Color.Red else Color(0xFFD4D4D4),
        animationSpec = tween(durationMillis = 300)
    )




    var passwd by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }

    val passwordBorderColor by animateColorAsState(
        targetValue = if (!isPasswordValid) Color.Red else Color(0xFFD4D4D4),
        animationSpec = tween(300)
    )

    var checkPasswd by remember { mutableStateOf("")}




    val greyColor = Color(0xFF525252)
    val typedTextColor = Color.Black





    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        )  {
            Image(
                painter = painterResource(id = R.drawable.logo), // ¡Reemplaza con el ID de tu icono!
                contentDescription = "Mi icono personalizado", // Descripción para accesibilidad
                modifier = Modifier
                    .size(50.dp)

            )
            Text(
                text = "Rob Store",
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Crear una cuenta",
                fontSize = 20.sp,
                color = greyColor,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        )  {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Nombre de usuario",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            hasFocus = focusState.isFocused
                            if (!focusState.isFocused) {
                                // Solo validamos cuando se pierde el foco
                                wasFocusedOnce = true
                                isEmailValid = validateEmail(email) || email.isEmpty()
                            }
                        },
                    placeholder = { Text("John") },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    isError = !isEmailValid && wasFocusedOnce,

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        errorBorderColor = Color.Red,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        errorTextColor = Color.Black
                    ),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                AnimatedVisibility(
                    visible = !isEmailValid && wasFocusedOnce,
                    enter = fadeIn(animationSpec = tween(300)) + slideInVertically(),
                    exit = fadeOut(animationSpec = tween(200)) + slideOutVertically()
                ) {
                    Text(
                        text = "Por favor, ingresa un correo válido.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Correo electrónico",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        if (!hasFocus) {
                            isEmailValid = validateEmail(it) || it.isEmpty()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            hasFocus = focusState.isFocused
                            if (!focusState.isFocused) {
                                // Solo validamos cuando se pierde el foco
                                wasFocusedOnce = true
                                isEmailValid = validateEmail(email) || email.isEmpty()
                            }
                        },
                    placeholder = { Text("example@gmail.com") },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    isError = !isEmailValid && wasFocusedOnce,

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        errorBorderColor = Color.Red,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        errorTextColor = Color.Black
                    ),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                AnimatedVisibility(
                    visible = !isEmailValid && wasFocusedOnce,
                    enter = fadeIn(animationSpec = tween(300)) + slideInVertically(),
                    exit = fadeOut(animationSpec = tween(200)) + slideOutVertically()
                ) {
                    Text(
                        text = "Por favor, ingresa un correo válido.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

            }

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ){
                Text(
                    text = "Contraseña",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = passwd,
                    onValueChange = {
                        passwd = it
                        if (isPasswordValid.not()) isPasswordValid = true // Resetear error mientras escribe
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("********") },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    isError = !isPasswordValid,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = icon, contentDescription = description)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        // Bordes
                        focusedBorderColor = Color(0xFF0073ED),
                        unfocusedBorderColor = passwordBorderColor,
                        disabledBorderColor = passwordBorderColor,
                        errorBorderColor = Color.Red,

                        // Texto escrito
                        focusedTextColor = typedTextColor,
                        unfocusedTextColor = typedTextColor,
                        disabledTextColor = Color.LightGray,
                        errorTextColor = typedTextColor,

                        // Label (si usas)
                        focusedLabelColor = Color(0xFF0073ED),
                        unfocusedLabelColor = Color.Gray,
                        disabledLabelColor = Color.LightGray,
                        errorLabelColor = Color.Red,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),

                    )
                AnimatedVisibility(
                    visible = !isPasswordValid,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Text(
                        text = "Por favor, ingresa una contraseña.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Confirmar contraseña",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = checkPasswd,
                    onValueChange = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            hasFocus = focusState.isFocused
                            if (!focusState.isFocused) {
                                // Solo validamos cuando se pierde el foco
                                wasFocusedOnce = true
                                isEmailValid = validateEmail(email) || email.isEmpty()
                            }
                        },
                    placeholder = { Text("********") },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    isError = !isEmailValid && wasFocusedOnce,

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        errorBorderColor = Color.Red,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        errorTextColor = Color.Black
                    ),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                AnimatedVisibility(
                    visible = !isEmailValid && wasFocusedOnce,
                    enter = fadeIn(animationSpec = tween(300)) + slideInVertically(),
                    exit = fadeOut(animationSpec = tween(200)) + slideOutVertically()
                ) {
                    Text(
                        text = "Por favor, ingresa un correo válido.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }
            Button(
                onClick = {
                    // Acción de login aquí
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(vertical = 10.dp),
                shape = RoundedCornerShape(12.dp), // Bordes redondeados
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F8B41), // Verde principal
                    contentColor = Color.White // Color del texto
                )
            ) {
                Text(text = "Iniciar Sesión", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            )  {
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "¿Ya tienes una cuenta?",
                    fontSize = 20.sp,
                    color = greyColor,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Iniciar sesión",
                    fontSize = 18.sp,
                    color = Color(0xFF3f8b41),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onNavigateToLogin()
                    }

                )



            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                horizontalAlignment = Alignment.CenterHorizontally,

            ){
                Text(
                    text = "Al registrarte, aceptas nuestros Términos y Condiciones y Política de Privacidad",
                    fontSize = 12.sp,
                    color = Color(0xFF737373),
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.9f) // opcional para no ocupar todo el ancho
                )
            }

        }
    }
}

