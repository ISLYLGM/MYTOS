package com.example.mytos.screens.pedidos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytos.data.SacolaData
import com.example.mytos.ui.components.MytosBottomBar
import java.text.SimpleDateFormat
import java.util.Locale

private val MytosPurple = Color(0xFF744B8F)
private val MytosPurpleDark = Color(0xFF432A55)
private val MytosCream = Color(0xFFFFF9F2)
private val MytosText = Color(0xFF302736)
private val MytosYellow = Color(0xFFF4C95D)

@Composable
fun PedidosScreen(
    navController: NavController
) {
    val pedidos = SacolaData.pedidos

    Scaffold(
        containerColor = MytosCream,
        bottomBar = {
            MytosBottomBar(navController = navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = MytosPurple
                    )
                }

                Text(
                    text = "Meus Pedidos",
                    color = MytosPurpleDark,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (pedidos.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Você ainda não fez nenhum pedido.",
                        color = MytosText.copy(alpha = 0.65f),
                        fontSize = 16.sp
                    )
                }

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        items = pedidos,
                        key = { it.id }
                    ) { pedido ->

                        val quantidadeItens = pedido.itens.sumOf {
                            it.quantidade
                        }

                        val dataFormatada = remember(pedido.data) {
                            SimpleDateFormat(
                                "dd/MM/yyyy • HH:mm",
                                Locale("pt", "BR")
                            ).format(pedido.data)
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        "pedido/${pedido.id}"
                                    )
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        text = "Pedido #${pedido.id}",
                                        color = MytosPurpleDark,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = "R$ %.2f".format(
                                            Locale("pt", "BR"),
                                            pedido.valorTotal
                                        ),
                                        color = MytosPurple,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = dataFormatada,
                                    color = MytosText.copy(alpha = 0.65f),
                                    fontSize = 13.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = "$quantidadeItens item(ns)",
                                    color = MytosText,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}