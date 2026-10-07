package com.example.mytos.screens.cadastro

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytos.R
import com.example.mytos.ui.theme.MytosCream
import com.example.mytos.ui.theme.MytosPurple
import com.example.mytos.ui.theme.MytosPurpleDark
import com.example.mytos.ui.theme.MytosText
import com.example.mytos.ui.theme.MytosTextSecondary

@Composable
fun CadastroScreen(
    onCadastroConcluido: () -> Unit,
    onVoltarLogin: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }

    var mostrarSenha by remember { mutableStateOf(false) }
    var mostrarConfirmacao by remember { mutableStateOf(false) }

    var tentouCadastrar by remember { mutableStateOf(false) }

    val nomeValido = nome.trim().length >= 3

    val emailValido = email.matches(
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    )

    val senhaValida =
        senha.length >= 6 &&
                senha.any { it.isLetter() } &&
                senha.any { it.isDigit() }

    val senhasIguais =
        senha.isNotEmpty() &&
                senha == confirmarSenha

    val formularioValido =
        nomeValido &&
                emailValido &&
                senhaValida &&
                senhasIguais

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MytosCream)
            .verticalScroll(rememberScrollState())
    ) {

        // ==========================================
        // TOPO ROXO
        // ==========================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
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
                    modifier = Modifier.size(88.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

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
                    top = 24.dp,
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // CENTRALIZADO
            Text(
                text = "Vamos começar sua jornada!",
                color = MytosPurpleDark,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Crie sua conta para descobrir\nsabores inspirados em mitologias.",
                color = MytosTextSecondary,
                fontSize = 16.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(18.dp))

            // ==========================================
            // NOME
            // ==========================================

            OutlinedTextField(
                value = nome,
                onValueChange = {
                    nome = it
                    tentouCadastrar = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Seu nome",
                        fontSize = 17.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Nome",
                        tint = MytosPurple
                    )
                },
                isError = tentouCadastrar && !nomeValido,
                shape = RoundedCornerShape(21.dp),
                colors = campoCadastro()
            )

            if (tentouCadastrar && !nomeValido) {
                Text(
                    text = "Digite seu nome com pelo menos 3 caracteres.",
                    color = Color(0xFFD94C59),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ==========================================
            // E-MAIL
            // ==========================================

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    tentouCadastrar = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
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
                isError = tentouCadastrar && !emailValido,
                shape = RoundedCornerShape(21.dp),
                colors = campoCadastro()
            )

            if (tentouCadastrar && !emailValido) {
                Text(
                    text = "Digite um e-mail válido.",
                    color = Color(0xFFD94C59),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ==========================================
            // SENHA
            // ==========================================

            OutlinedTextField(
                value = senha,
                onValueChange = {
                    senha = it
                    tentouCadastrar = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Senha (mínimo 6 caracteres)",
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
                            tint = MytosPurple
                        )
                    }
                },
                visualTransformation = if (mostrarSenha) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                isError = tentouCadastrar && !senhaValida,
                shape = RoundedCornerShape(21.dp),
                colors = campoCadastro()
            )

            if (tentouCadastrar && !senhaValida) {
                Text(
                    text = "Use pelo menos 6 caracteres, uma letra e um número.",
                    color = Color(0xFFD94C59),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ==========================================
            // CONFIRMAR SENHA
            // ==========================================

            OutlinedTextField(
                value = confirmarSenha,
                onValueChange = {
                    confirmarSenha = it
                    tentouCadastrar = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Confirme sua senha",
                        fontSize = 17.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirmar senha",
                        tint = MytosPurple
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            mostrarConfirmacao = !mostrarConfirmacao
                        }
                    ) {
                        Icon(
                            imageVector = if (mostrarConfirmacao) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = if (mostrarConfirmacao) {
                                "Ocultar confirmação da senha"
                            } else {
                                "Mostrar confirmação da senha"
                            },
                            tint = MytosPurple
                        )
                    }
                },
                visualTransformation = if (mostrarConfirmacao) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                isError = tentouCadastrar && !senhasIguais,
                shape = RoundedCornerShape(21.dp),
                colors = campoCadastro()
            )

            if (tentouCadastrar && !senhasIguais) {
                Text(
                    text = "As senhas precisam ser iguais.",
                    color = Color(0xFFD94C59),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // CRIAR CONTA
            // ==========================================

            Button(
                onClick = {
                    tentouCadastrar = true

                    if (formularioValido) {
                        onCadastroConcluido()
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
                    text = "CRIAR CONTA",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            // ==========================================
            // VOLTAR PARA LOGIN
            // ==========================================

            TextButton(
                onClick = onVoltarLogin
            ) {
                Text(
                    text = "Já possui uma conta? Entrar",
                    color = MytosPurple,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun campoCadastro() =
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