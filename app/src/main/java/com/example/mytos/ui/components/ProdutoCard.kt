package com.example.mytos.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytos.model.Produto
import com.example.mytos.ui.theme.MytosPurple
import com.example.mytos.ui.theme.MytosText
import java.util.Locale

@Composable
fun ProdutoCard(
    produto: Produto,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .semantics {
                contentDescription = "Produto ${produto.nome}"
            },
        colors = CardDefaults.cardColors(
            containerColor = MytosPurple.copy(alpha = 0.08f)
        )
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
                    fontSize = 18.sp
                )

                Text(
                    text = produto.descricao,
                    color = MytosText.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )

                Text(
                    text = String.format(
                        Locale("pt", "BR"),
                        "R$ %.2f",
                        produto.preco
                    ),
                    color = MytosPurple,
                    fontSize = 16.sp
                )
            }

            IconButton(
                onClick = onRemove
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remover ${produto.nome}",
                    tint = MytosPurple
                )
            }
        }
    }
}