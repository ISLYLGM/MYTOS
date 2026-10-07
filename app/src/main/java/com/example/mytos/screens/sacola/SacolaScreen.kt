package com.example.mytos.screens.sacola

import com.example.mytos.data.SacolaData
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytos.model.ItemSacola
import com.example.mytos.ui.theme.MytosTheme
import java.util.Locale

// Paleta do Splash
private val PurpleTop = Color(0xFF3A1A63)
private val PurpleMid = Color(0xFF2A1248)
private val PurpleBottom = Color(0xFF1B0B33)

private val Cream = Color(0xFFFBF3E4)
private val ProgressPurple = Color(0xFF9B6DDB)

// Locale correto para formato de moeda brasileira sem avisos de descontinuação
private val localePtBR = Locale.forLanguageTag("pt-BR")

@Composable
fun SacolaScreen(
    onNavegarParaMeusPedidos: () -> Unit
) {
    val itens = SacolaData.itensSacola
    val valorTotal = SacolaData.calcularTotal()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        PurpleTop,
                        PurpleMid,
                        PurpleBottom
                    )
                )
            )
    ) {
        // Montanhas decorativas ao fundo
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val w = size.width
            val h = size.height

            // Montanhas do fundo
            val back = Path().apply {
                moveTo(0f, h)
                lineTo(0f, h * 0.78f)
                lineTo(w * 0.22f, h * 0.68f)
                lineTo(w * 0.40f, h * 0.76f)
                lineTo(w * 0.62f, h * 0.64f)
                lineTo(w * 0.82f, h * 0.74f)
                lineTo(w, h * 0.66f)
                lineTo(w, h)
                close()
            }

            drawPath(
                path = back,
                color = Color.White.copy(alpha = 0.05f)
            )

            // Montanhas da frente
            val front = Path().apply {
                moveTo(0f, h)
                lineTo(0f, h * 0.86f)
                lineTo(w * 0.30f, h * 0.78f)
                lineTo(w * 0.55f, h * 0.87f)
                lineTo(w * 0.78f, h * 0.80f)
                lineTo(w, h * 0.88f)
                lineTo(w, h)
                close()
            }

            drawPath(
                path = front,
                color = Color.White.copy(alpha = 0.07f)
            )
        }

        // Conteúdo da Tela da Sacola
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Minha Sacola",
                color = Cream,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (itens.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sua sacola está vazia.",
                        color = Cream.copy(alpha = 0.7f),
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(itens, key = { it.produto.id }) { item ->
                        SacolaItemCard(
                            itemSacola = item,
                            onAumentar = { SacolaData.aumentarQuantidade(item.produto.id) },
                            onDiminuir = { SacolaData.diminuirQuantidade(item.produto.id) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card de Resumo do Pedido e Botão
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = PurpleMid.copy(alpha = 0.9f)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Valor Total",
                                color = Cream,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = String.format(localePtBR, "R$ %.2f", valorTotal),
                                color = Cream,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                val pedidoCriado = SacolaData.finalizarPedido()
                                if (pedidoCriado != null) {
                                    onNavegarParaMeusPedidos()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ProgressPurple),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Finalizar pedido",
                                color = Cream,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SacolaItemCard(
    itemSacola: ItemSacola,
    onAumentar: () -> Unit,
    onDiminuir: () -> Unit
) {
    val produto = itemSacola.produto

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = "Item ${produto.nome}, quantidade ${itemSacola.quantidade}"
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.08f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = produto.nome,
                    color = Cream,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = String.format(localePtBR, "Preço: R$ %.2f", produto.preco),
                    color = Cream.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )

                Text(
                    text = String.format(localePtBR, "Subtotal: R$ %.2f", itemSacola.subtotal),
                    color = ProgressPurple,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Controles de Quantidade (+ / −)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = onDiminuir, modifier = Modifier.size(36.dp)) {
                    Text(text = "−", color = Cream, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }

                Text(
                    text = "${itemSacola.quantidade}",
                    color = Cream,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                IconButton(onClick = onAumentar, modifier = Modifier.size(36.dp)) {
                    Text(text = "+", color = Cream, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SacolaScreenPreview() {
    MytosTheme {
        SacolaScreen(
            onNavegarParaMeusPedidos = {}
        )
    }
}