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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytos.ui.components.MytosBottomBar
import com.example.mytos.ui.theme.MytosCream
import com.example.mytos.ui.theme.MytosPurple
import com.example.mytos.ui.theme.MytosPurpleDark
import com.example.mytos.ui.theme.MytosText

@Composable
fun PerfilScreen(
    navController: NavController
) {
    // Dados Padrão / Genéricos do Usuário
    var nome by remember { mutableStateOf("Cliente MYTOS") }
    var email by remember { mutableStateOf("cliente@mytos.com") }

    // Dados Padrão / Genéricos do Cartão
    var numeroCartao by remember { mutableStateOf("•••• •••• •••• 1234") }
    var nomeTitular by remember { mutableStateOf("NOME NO CARTAO") }
    var validadeCartao by remember { mutableStateOf("12/30") }

    // Controle do Modo de Edição
    var editandoPerfil by remember { mutableStateOf(false) }
    var editandoCartao by remember { mutableStateOf(false) }

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

            // Cabeçalho com Botão Voltar quando estiver editando
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (editandoPerfil || editandoCartao) {
                    IconButton(
                        onClick = {
                            editandoPerfil = false
                            editandoCartao = false
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

            Spacer(modifier = Modifier.height(20.dp))

            // Card de Dados do Usuário
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

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = email,
                            color = MytosText.copy(alpha = 0.8f),
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { editandoPerfil = true },
                            colors = ButtonDefaults.buttonColors(containerColor = MytosPurple),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Editar perfil")
                        }
                    } else {
                        Text(
                            text = "Alterar dados pessoais",
                            color = MytosPurpleDark,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = nome,
                            onValueChange = { nome = it },
                            label = { Text("Nome") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("E-mail") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { editandoPerfil = false },
                            colors = ButtonDefaults.buttonColors(containerColor = MytosPurple),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Salvar alterações")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Seção de Meus Pedidos
            Text(
                text = "Atividades",
                color = MytosText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { navController.navigate("pedidos") },
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
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Meus Pedidos",
                    color = MytosPurple,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Seção de Formas de Pagamento
            Text(
                text = "Formas de pagamento",
                color = MytosText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MytosPurple.copy(alpha = 0.06f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CreditCard,
                            contentDescription = null,
                            tint = MytosPurple
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cartão de Crédito",
                            color = MytosPurpleDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

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

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { editandoCartao = true },
                            colors = ButtonDefaults.buttonColors(containerColor = MytosPurple),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = "Cadastrar/Editar cartão")
                        }
                    } else {
                        OutlinedTextField(
                            value = numeroCartao,
                            onValueChange = { numeroCartao = it },
                            label = { Text("Número do Cartão") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = nomeTitular,
                            onValueChange = { nomeTitular = it },
                            label = { Text("Nome do Titular") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = validadeCartao,
                            onValueChange = { validadeCartao = it },
                            label = { Text("Validade (MM/AA)") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MytosPurple,
                                focusedLabelColor = MytosPurple
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { editandoCartao = false },
                            colors = ButtonDefaults.buttonColors(containerColor = MytosPurple),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Salvar cartão")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botão Sair da conta
            Button(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("perfil") {
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
                Text(text = "Sair da conta")
            }
        }
    }
}