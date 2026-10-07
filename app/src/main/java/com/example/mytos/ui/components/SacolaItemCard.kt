package com.example.mytos.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytos.model.ItemSacola
import com.example.mytos.ui.theme.MytosPurple
import com.example.mytos.ui.theme.MytosText
import java.util.Locale

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
            containerColor = MytosPurple.copy(alpha = 0.08f)
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
                    color = MytosText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = String.format(Locale("pt", "BR"), "R$ %.2f", produto.preco),
                    color = MytosText.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )

                Text(
                    text = String.format(Locale("pt", "BR"), "Subtotal: R$ %.2f", itemSacola.subtotal),
                    color = MytosPurple,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Botões + e -
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = onDiminuir, modifier = Modifier.size(36.dp)) {
                    Text(text = "−", color = MytosPurple, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }

                Text(
                    text = "${itemSacola.quantidade}",
                    color = MytosText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                IconButton(onClick = onAumentar, modifier = Modifier.size(36.dp)) {
                    Text(text = "+", color = MytosPurple, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}