package com.example.mytos.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Meu perfil",
                color = MytosPurple,
                fontSize = 30.sp
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MytosPurple.copy(alpha = 0.10f)
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Olá, usuário! 👋",
                        color = MytosPurpleDark,
                        fontSize = 22.sp
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Bem-vindo ao seu espaço no MYTOS.",
                        color = MytosText,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Minha conta",
                color = MytosText,
                fontSize = 20.sp
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

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
                )
            ) {
                Text(
                    text = "Sair da conta"
                )
            }
        }
    }
}