package com.example.mytos.screens.pedidos

import androidx.compose.foundation.layout.Arrangement
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

@Composable
fun PedidoDetalhesScreen(
    navController: NavController,
    pedidoId: String
) {
    val pedido = SacolaData.pedidos.find {
        it.id == pedidoId
    }

    Scaffold(
        containerColor = MytosCream,
        bottomBar = {
            MytosBottomBar(navController = navController)
        }
    ) { paddingValues ->

        if (pedido == null) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Pedido não encontrado.",
                    color = MytosText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Volte para Meus Pedidos e tente novamente.",
                    color = MytosText.copy(alpha = 0.7f)
                )
            }

        } else {

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
                        text = "Detalhes do Pedido",
                        color = MytosPurpleDark,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Pedido #${pedido.id}",
                    color = MytosPurple,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = SimpleDateFormat(
                        "dd/MM/yyyy • HH:mm",
                        Locale("pt", "BR")
                    ).format(pedido.data),
                    color = MytosText.copy(alpha = 0.65f),
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    items(
                        items = pedido.itens,
                        key = {
                            "${pedido.id}-${it.produto.id}"
                        }
                    ) { item ->

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    text = item.produto.nome,
                                    color = MytosPurpleDark,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Quantidade: ${item.quantidade}",
                                    color = MytosText,
                                    fontSize = 14.sp
                                )

                                Text(
                                    text = "Preço unitário: R$ %.2f".format(
                                        Locale("pt", "BR"),
                                        item.produto.preco
                                    ),
                                    color = MytosText.copy(alpha = 0.7f),
                                    fontSize = 13.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Subtotal: R$ %.2f".format(
                                        Locale("pt", "BR"),
                                        item.subtotal
                                    ),
                                    color = MytosPurple,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MytosPurple
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Total",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "R$ %.2f".format(
                                Locale("pt", "BR"),
                                pedido.valorTotal
                            ),
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }
        }
    }
}