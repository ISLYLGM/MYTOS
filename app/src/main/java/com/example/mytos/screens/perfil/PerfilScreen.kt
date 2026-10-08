package com.example.mytos.screens.perfil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytos.navigation.Rotas
import com.example.mytos.ui.components.MytosBottomBar
import com.example.mytos.ui.theme.MytosCream
import com.example.mytos.ui.theme.MytosPurple
import com.example.mytos.ui.theme.MytosPurpleDark
import com.example.mytos.ui.theme.MytosText

@Composable
fun PerfilScreen(
    navController: NavController
) {

    val focusManager = LocalFocusManager.current

    // ===============================
    // DADOS DO USUÁRIO
    // ===============================

    var nome by remember {
        mutableStateOf("Cliente MYTOS")
    }

    var email by remember {
        mutableStateOf("cliente@mytos.com")
    }

    // ===============================
    // DADOS DO CARTÃO
    // ===============================

    var numeroCartao by remember {
        mutableStateOf("•••• •••• •••• 1234")
    }

    var nomeTitular by remember {
        mutableStateOf("NOME NO CARTAO")
    }

    var validadeCartao by remember {
        mutableStateOf("12/30")
    }

    // ===============================
    // CONTROLE DE EDIÇÃO
    // ===============================

    var editandoPerfil by remember {
        mutableStateOf(false)
    }

    var editandoCartao by remember {
        mutableStateOf(false)
    }

    // ===============================
    // MENSAGENS DE ERRO
    // ===============================

    var erroNome by remember {
        mutableStateOf(false)
    }

    var erroEmail by remember {
        mutableStateOf(false)
    }

    var erroNumeroCartao by remember {
        mutableStateOf(false)
    }

    var erroNomeTitular by remember {
        mutableStateOf(false)
    }

    var erroValidade by remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            MytosBottomBar(
                navController = navController
            )
        },
        containerColor = MytosCream
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {

            // ===============================
            // CABEÇALHO
            // ===============================

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (editandoPerfil || editandoCartao) {

                    IconButton(
                        onClick = {
                            editandoPerfil = false
                            editandoCartao = false
                            focusManager.clearFocus()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = MytosPurple
                        )
                    }
                }

                Text(
                    text = "Meu perfil",
                    color = MytosPurple,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // ===============================
            // DADOS DO USUÁRIO
            // ===============================

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MytosPurple.copy(alpha = 0.10f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    if (!editandoPerfil) {

                        Text(
                            text = "Olá, $nome! 👋",
                            color = MytosPurpleDark,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = email,
                            color = MytosText.copy(alpha = 0.8f),
                            fontSize = 15.sp
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Button(
                            onClick = {
                                editandoPerfil = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MytosPurple
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text(
                                text = "Editar perfil"
                            )
                        }

                    } else {

                        Text(
                            text = "Alterar dados pessoais",
                            color = MytosPurpleDark,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        // ===============================
                        // NOME
                        // ===============================

                        OutlinedTextField(
                            value = nome,
                            onValueChange = {
                                nome = it
                                erroNome = false
                            },
                            label = {
                                Text(
                                    text = "Nome",
                                    color = MytosText
                                )
                            },
                            singleLine = true,
                            isError = erroNome,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple,
                                focusedTextColor = MytosText,
                                unfocusedTextColor = MytosText,
                                cursorColor = MytosPurple,
                                errorTextColor = MytosText
                            )
                        )

                        if (erroNome) {
                            Text(
                                text = "O nome não pode ficar vazio.",
                                color = MytosPurpleDark,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        // ===============================
                        // E-MAIL
                        // ===============================

                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                erroEmail = false
                            },
                            label = {
                                Text(
                                    text = "E-mail",
                                    color = MytosText
                                )
                            },
                            singleLine = true,
                            isError = erroEmail,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple,
                                focusedTextColor = MytosText,
                                unfocusedTextColor = MytosText,
                                cursorColor = MytosPurple,
                                errorTextColor = MytosText
                            )
                        )

                        if (erroEmail) {
                            Text(
                                text = "O e-mail não pode ficar vazio.",
                                color = MytosPurpleDark,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Button(
                            onClick = {

                                erroNome = nome.trim().isEmpty()
                                erroEmail = email.trim().isEmpty()

                                if (!erroNome && !erroEmail) {
                                    editandoPerfil = false
                                    focusManager.clearFocus()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MytosPurple
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text(
                                text = "Salvar alterações"
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // ===============================
            // ATIVIDADES
            // ===============================

            Text(
                text = "Atividades",
                color = MytosText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedButton(
                onClick = {
                    navController.navigate(Rotas.Pedidos)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ListAlt,
                    contentDescription = null,
                    tint = MytosPurple
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = "Meus Pedidos",
                    color = MytosPurple,
                    fontSize = 16.sp
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // ===============================
            // FORMAS DE PAGAMENTO
            // ===============================

            Text(
                text = "Formas de pagamento",
                color = MytosText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MytosPurple.copy(alpha = 0.06f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.CreditCard,
                            contentDescription = null,
                            tint = MytosPurple
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            text = "Cartão de Crédito",
                            color = MytosPurpleDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    if (!editandoCartao) {

                        Text(
                            text = numeroCartao,
                            color = MytosText,
                            fontSize = 15.sp
                        )

                        Text(
                            text = "Titular: $nomeTitular",
                            color = MytosText.copy(alpha = 0.7f),
                            fontSize = 13.sp
                        )

                        Text(
                            text = "Validade: $validadeCartao",
                            color = MytosText.copy(alpha = 0.7f),
                            fontSize = 13.sp
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Button(
                            onClick = {
                                editandoCartao = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MytosPurple
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {

                            Text(
                                text = "Cadastrar/Editar cartão"
                            )
                        }

                    } else {

                        // ===============================
                        // NÚMERO DO CARTÃO
                        // ===============================

                        OutlinedTextField(
                            value = numeroCartao,
                            onValueChange = { novoValor ->

                                val somenteNumeros =
                                    novoValor.filter { it.isDigit() }

                                if (somenteNumeros.length <= 16) {
                                    numeroCartao = somenteNumeros
                                    erroNumeroCartao = false
                                }
                            },
                            label = {
                                Text(
                                    text = "Número do Cartão",
                                    color = MytosText
                                )
                            },
                            singleLine = true,
                            isError = erroNumeroCartao,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple,
                                focusedTextColor = MytosText,
                                unfocusedTextColor = MytosText,
                                cursorColor = MytosPurple,
                                errorTextColor = MytosText
                            )
                        )

                        if (erroNumeroCartao) {
                            Text(
                                text = "Informe o número do cartão.",
                                color = MytosPurpleDark,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        // ===============================
                        // NOME DO TITULAR
                        // ===============================

                        OutlinedTextField(
                            value = nomeTitular,
                            onValueChange = { novoValor ->

                                val somenteLetras =
                                    novoValor.filter {
                                        it.isLetter() || it == ' '
                                    }

                                nomeTitular = somenteLetras
                                erroNomeTitular = false
                            },
                            label = {
                                Text(
                                    text = "Nome do Titular",
                                    color = MytosText
                                )
                            },
                            singleLine = true,
                            isError = erroNomeTitular,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple,
                                focusedTextColor = MytosText,
                                unfocusedTextColor = MytosText,
                                cursorColor = MytosPurple,
                                errorTextColor = MytosText
                            )
                        )

                        if (erroNomeTitular) {
                            Text(
                                text = "Informe o nome do titular.",
                                color = MytosPurpleDark,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        // ===============================
                        // VALIDADE
                        // ===============================

                        OutlinedTextField(
                            value = validadeCartao,
                            onValueChange = { novoValor ->

                                val numeros =
                                    novoValor.filter { it.isDigit() }

                                if (numeros.length <= 4) {

                                    validadeCartao = when {
                                        numeros.length <= 2 -> {
                                            numeros
                                        }

                                        else -> {
                                            "${numeros.substring(0, 2)}/${numeros.substring(2)}"
                                        }
                                    }

                                    erroValidade = false
                                }
                            },
                            label = {
                                Text(
                                    text = "Validade (MM/AA)",
                                    color = MytosText
                                )
                            },
                            singleLine = true,
                            isError = erroValidade,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple,
                                focusedTextColor = MytosText,
                                unfocusedTextColor = MytosText,
                                cursorColor = MytosPurple,
                                errorTextColor = MytosText
                            )
                        )

                        if (erroValidade) {
                            Text(
                                text = "Informe a validade no formato MM/AA.",
                                color = MytosPurpleDark,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        // ===============================
                        // SALVAR CARTÃO
                        // ===============================

                        Button(
                            onClick = {

                                val numerosCartao =
                                    numeroCartao.filter { it.isDigit() }

                                val validadeValida =
                                    validadeCartao.matches(
                                        Regex("""(0[1-9]|1[0-2])/[0-9]{2}""")
                                    )

                                erroNumeroCartao =
                                    numerosCartao.isEmpty()

                                erroNomeTitular =
                                    nomeTitular.trim().isEmpty()

                                erroValidade =
                                    !validadeValida

                                if (
                                    !erroNumeroCartao &&
                                    !erroNomeTitular &&
                                    !erroValidade
                                ) {
                                    editandoCartao = false
                                    focusManager.clearFocus()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MytosPurple
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text(
                                text = "Salvar cartão"
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // ===============================
            // SAIR DA CONTA
            // ===============================

            Button(
                onClick = {

                    focusManager.clearFocus()

                    navController.navigate(Rotas.Login) {

                        popUpTo(Rotas.Perfil) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MytosPurple
                ),
                shape = RoundedCornerShape(12.dp)
            ) {

                Text(
                    text = "Sair da conta"
                )
            }
        }
    }
}