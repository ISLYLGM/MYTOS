package com.example.mytos.screens.produto

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytos.data.adicionarNaSacola
import com.example.mytos.data.produtos
import java.util.Locale


// =============================================================
// CORES
// =============================================================

private val Fundo = Color(0xFFFFF9F0)

private val RoxoTopo = Color(0xFF4E2870)
private val RoxoTopoEscuro = Color(0xFF34164D)

private val Roxo = Color(0xFF65309A)
private val RoxoTexto = Color(0xFF342047)

private val TextoCinza = Color(0xFF716C76)

private val LilasCard = Color(0xFFF6EFF8)

private val Amarelo = Color(0xFFF5D77A)

private val VerdeClaro = Color(0xFFE5F2D8)


// =============================================================
// TELA DE DETALHES
// =============================================================

@Composable
fun ProdutoDetalhesScreen(
    navController: NavController,
    produtoId: Int
) {

    // =========================================================
    // PRODUTO
    // =========================================================

    val produto = produtos.find {
        it.id == produtoId
    }

    if (produto == null) {
        navController.popBackStack()
        return
    }


    // =========================================================
    // CATEGORIA
    // =========================================================

    val categoria = when (produto.categoriaId) {
        1 -> "CAFÉS"
        2 -> "DOCES"
        3 -> "SALGADOS"
        else -> "PRODUTOS"
    }


    // =========================================================
    // DESCRIÇÃO
    // =========================================================

    val descricao = when (produto.id) {

        1 -> "Espresso intenso com notas de chocolate."

        2 -> "Uma bebida refrescante inspirada nos mares."

        3 -> "Uma torta delicada inspirada na rainha dos deuses."

        4 -> "Chá delicado inspirado nas lendárias kitsunes."

        else -> produto.descricao
    }


    // =========================================================
    // INSPIRAÇÃO
    // =========================================================

    val inspiracao = when (produto.id) {

        1 ->
            "Inspirado em Apolo, deus grego associado ao Sol e às artes. Um espresso encorpado para iluminar a pausa."

        2 ->
            "Inspirado em Poseidon, deus dos mares. Uma bebida refrescante para acompanhar sua pausa."

        3 ->
            "Inspirada em Hera, rainha dos deuses gregos. Uma sobremesa especial para tornar sua pausa ainda mais deliciosa."

        4 ->
            "Inspirado nas lendárias kitsunes da mitologia japonesa. Um chá delicado para tornar sua pausa ainda mais especial."

        else ->
            produto.descricao
    }


    Scaffold(
        containerColor = Fundo
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(
                    rememberScrollState()
                )
                .navigationBarsPadding()
        ) {

            // =====================================================
            // CONTAINER ROXO
            // ALTURA DEFINIDA PELO USUÁRIO: 250 DP
            // =====================================================

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                RoxoTopo,
                                RoxoTopoEscuro
                            )
                        )
                    )
            ) {

                // =================================================
                // BOTÃO VOLTAR
                // =================================================

                Box(
                    modifier = Modifier
                        .padding(
                            start = 22.dp,
                            top = 12.dp
                        )
                        .size(48.dp)
                        .background(
                            Color.White.copy(alpha = 0.12f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White,
                            modifier = Modifier.size(27.dp)
                        )
                    }
                }


                // =================================================
                // BOLINHA AMARELA
                // =================================================

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(
                            top = 72.dp,
                            end = 57.dp
                        )
                        .size(17.dp)
                        .background(
                            Amarelo,
                            CircleShape
                        )
                )


                // =================================================
                // BOLINHA VERDE
                // =================================================

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 43.dp)
                        .offset(y = 10.dp)
                        .size(13.dp)
                        .background(
                            VerdeClaro,
                            CircleShape
                        )
                )


                // =================================================
                // CÍRCULO EXTERNO
                // PEQUENO PARA CABER NO TOPO DE 250DP
                // =================================================

                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 57.dp)
                        .size(116.dp)
                        .background(
                            Color(0xFF77539A),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    // =============================================
                    // CÍRCULO BRANCO
                    // =============================================

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                Color.White,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        // =========================================
                        // ÍCONE DE CAFÉ
                        // =========================================

                        Icon(
                            imageVector = Icons.Default.Coffee,
                            contentDescription = "Café",
                            tint = Roxo,
                            modifier = Modifier.size(31.dp)
                        )
                    }
                }


                // =================================================
                // FRASE
                // =================================================

                Text(
                    text = "SABOR INSPIRADO EM MITOLOGIAS",

                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp),

                    color = Color.White.copy(
                        alpha = 0.9f
                    ),

                    fontSize = 9.sp,

                    fontWeight = FontWeight.Bold,

                    letterSpacing = 0.9.sp
                )
            }


            // =====================================================
            // CONTEÚDO DO PRODUTO
            // =====================================================

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 43.dp,
                        end = 27.dp,
                        top = 27.dp,
                        bottom = 30.dp
                    )
            ) {

                // =================================================
                // CATEGORIA
                // =================================================

                Text(
                    text = categoria,

                    color = Roxo,

                    fontSize = 10.sp,

                    fontWeight = FontWeight.Bold,

                    letterSpacing = 1.1.sp
                )


                Spacer(
                    modifier = Modifier.height(17.dp)
                )


                // =================================================
                // NOME DO PRODUTO
                // =================================================

                Text(
                    text = produto.nome,

                    color = RoxoTexto,

                    fontSize = 28.sp,

                    fontWeight = FontWeight.Bold,

                    lineHeight = 33.sp
                )


                Spacer(
                    modifier = Modifier.height(13.dp)
                )


                // =================================================
                // AVALIAÇÃO
                // =================================================

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "★★★★★",

                        color = Amarelo,

                        fontSize = 16.sp
                    )

                    Spacer(
                        modifier = Modifier.width(9.dp)
                    )

                    Text(
                        text = "Uma escolha especial do MYTOS",

                        color = TextoCinza,

                        fontSize = 10.sp
                    )
                }


                Spacer(
                    modifier = Modifier.height(23.dp)
                )


                // =================================================
                // DESCRIÇÃO
                // =================================================

                Text(
                    text = descricao,

                    color = TextoCinza,

                    fontSize = 15.sp,

                    lineHeight = 22.sp
                )


                Spacer(
                    modifier = Modifier.height(25.dp)
                )


                // =================================================
                // CARD A INSPIRAÇÃO
                // =================================================

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            RoundedCornerShape(23.dp)
                        )
                        .padding(
                            start = 27.dp,
                            end = 23.dp,
                            top = 21.dp,
                            bottom = 21.dp
                        )
                ) {

                    Text(
                        text = "A inspiração",

                        color = RoxoTexto,

                        fontSize = 20.sp,

                        fontWeight = FontWeight.Bold
                    )


                    Spacer(
                        modifier = Modifier.height(13.dp)
                    )


                    Text(
                        text = inspiracao,

                        color = TextoCinza,

                        fontSize = 13.sp,

                        lineHeight = 21.sp
                    )
                }


                Spacer(
                    modifier = Modifier.height(17.dp)
                )


                // =================================================
                // CARD FEITO PARA SUA PAUSA
                // =================================================

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            LilasCard,
                            RoundedCornerShape(21.dp)
                        )
                        .padding(
                            horizontal = 14.dp,
                            vertical = 11.dp
                        ),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    // =============================================
                    // ÍCONE
                    // =============================================

                    Box(
                        modifier = Modifier
                            .size(43.dp)
                            .background(
                                VerdeClaro,
                                RoundedCornerShape(14.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Coffee,

                            contentDescription = null,

                            tint = Roxo,

                            modifier = Modifier.size(21.dp)
                        )
                    }


                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )


                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Feito para sua pausa",

                            color = RoxoTexto,

                            fontSize = 13.sp,

                            fontWeight = FontWeight.Bold
                        )


                        Spacer(
                            modifier = Modifier.height(2.dp)
                        )


                        Text(
                            text = "Consulte a equipe sobre ingredientes e alergênicos.",

                            color = TextoCinza,

                            fontSize = 9.sp,

                            lineHeight = 13.sp
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(27.dp)
                )


                // =================================================
                // PREÇO + BOTÃO
                // =================================================

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically,

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    // =============================================
                    // PREÇO
                    // =============================================

                    Column {

                        Text(
                            text = "Preço",

                            color = TextoCinza,

                            fontSize = 12.sp
                        )


                        Spacer(
                            modifier = Modifier.height(1.dp)
                        )


                        Text(
                            text = String.format(
                                Locale(
                                    "pt",
                                    "BR"
                                ),
                                "R$ %.2f",
                                produto.preco
                            ),

                            color = RoxoTexto,

                            fontSize = 24.sp,

                            fontWeight = FontWeight.Bold
                        )
                    }


                    // =============================================
                    // BOTÃO ADICIONAR
                    // =============================================

                    Button(
                        onClick = {
                            adicionarNaSacola(
                                produto.id
                            )
                        },

                        modifier = Modifier
                            .height(44.dp)
                            .width(157.dp),

                        shape = RoundedCornerShape(17.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Roxo
                        ),

                        contentPadding = androidx.compose.foundation.layout.PaddingValues(
                            horizontal = 14.dp
                        )
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ShoppingBag,

                            contentDescription =
                                "Adicionar à sacola",

                            tint = Color.White,

                            modifier = Modifier.size(18.dp)
                        )


                        Spacer(
                            modifier = Modifier.width(7.dp)
                        )


                        Text(
                            text = "Adicionar",

                            color = Color.White,

                            fontSize = 13.sp,

                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}