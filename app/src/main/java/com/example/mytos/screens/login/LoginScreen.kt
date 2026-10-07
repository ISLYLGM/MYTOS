package com.example.mytos.screens.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.example.mytos.R
import com.example.mytos.ui.theme.MytosCream
import com.example.mytos.ui.theme.MytosPurple
import com.example.mytos.ui.theme.MytosPurpleDark
import com.example.mytos.ui.theme.MytosText
import com.example.mytos.ui.theme.MytosTextSecondary

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onCadastro: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }
    var tentouEntrar by remember { mutableStateOf(false) }

    val emailValido = email.matches(
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    )

    val senhaValida = senha.length >= 6

    val formularioValido = emailValido && senhaValida

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MytosCream)
    ) {

        // ==========================================
        // TOPO ROXO
        // ==========================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    color = MytosPurpleDark,
                    shape = RoundedCornerShape(
                        bottomStart = 50.dp,
                        bottomEnd = 50.dp
                    )
                ),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.mytos_logo_white
                    ),
                    contentDescription = "Logo MYTOS",
                    modifier = Modifier.size(95.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "CAFÉ • SABOR • MITOLOGIA",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.8.sp
                )
            }
        }

        // ==========================================
        // CONTEÚDO
        // ==========================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 28.dp,
                    end = 28.dp,
                    top = 28.dp,
                    bottom = 12.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Que bom ter você aqui!",
                color = MytosPurpleDark,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "Entre na sua conta e escolha\nsua próxima aventura saborosa.",
                color = MytosTextSecondary,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // E-MAIL
            // ==========================================

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    tentouEntrar = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "E-mail",
                        fontSize = 17.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "E-mail",
                        tint = MytosPurple
                    )
                },
                isError = tentouEntrar && !emailValido,
                shape = RoundedCornerShape(22.dp),
                colors = campoLogin()
            )

            if (tentouEntrar && !emailValido) {
                Text(
                    text = "Digite um e-mail válido.",
                    color = Color(0xFFD94C59),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 3.dp
                        )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ==========================================
            // SENHA
            // ==========================================

            OutlinedTextField(
                value = senha,
                onValueChange = {
                    senha = it
                    tentouEntrar = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Senha",
                        fontSize = 17.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Senha",
                        tint = MytosPurple
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            mostrarSenha = !mostrarSenha
                        }
                    ) {
                        Icon(
                            imageVector = if (mostrarSenha) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = if (mostrarSenha) {
                                "Ocultar senha"
                            } else {
                                "Mostrar senha"
                            },
                            tint = MytosTextSecondary
                        )
                    }
                },
                visualTransformation = if (mostrarSenha) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                isError = tentouEntrar && !senhaValida,
                shape = RoundedCornerShape(22.dp),
                colors = campoLogin()
            )

            if (tentouEntrar && !senhaValida) {
                Text(
                    text = "A senha deve ter pelo menos 6 caracteres.",
                    color = Color(0xFFD94C59),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 3.dp
                        )
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ==========================================
            // BOTÃO ENTRAR
            // ==========================================

            Button(
                onClick = {
                    tentouEntrar = true

                    if (formularioValido) {
                        onLogin()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(19.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MytosPurple,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "ENTRAR",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }

            Spacer(modifier = Modifier.height(13.dp))

            // ==========================================
            // CRIAR CONTA
            // ==========================================

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Primeira visita?",
                    color = MytosTextSecondary,
                    fontSize = 15.sp
                )

                TextButton(
                    onClick = onCadastro
                ) {
                    Text(
                        text = "Criar conta",
                        color = MytosPurple,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            // ==========================================
            // INSTAGRAM
            // ==========================================

            TextButton(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "https://www.instagram.com/bia__e__bel/"
                        )
                    )

                    context.startActivity(intent)
                }
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.ic_instagram
                    ),
                    contentDescription = "Instagram",
                    modifier = Modifier.size(22.dp)
                )

                Spacer(
                    modifier = Modifier.size(7.dp)
                )

                Text(
                    text = "Confira nosso Instagram",
                    color = MytosPurple,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun campoLogin() =
    OutlinedTextFieldDefaults.colors(
        focusedTextColor = MytosText,
        unfocusedTextColor = MytosText,

        focusedBorderColor = MytosPurple,
        unfocusedBorderColor = Color(0xFFDCD5DF),

        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,

        cursorColor = MytosPurple,

        focusedPlaceholderColor = MytosTextSecondary,
        unfocusedPlaceholderColor = MytosTextSecondary,

        errorBorderColor = Color(0xFFD94C59),
        errorCursorColor = Color(0xFFD94C59)
    )