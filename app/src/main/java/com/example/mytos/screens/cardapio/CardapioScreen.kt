package com.example.mytos.screens.cardapio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytos.data.produtos
import com.example.mytos.model.Produto
import com.example.mytos.navigation.Rotas
import com.example.mytos.ui.components.MytosBottomBar
import com.example.mytos.ui.theme.MytosCream
import com.example.mytos.ui.theme.MytosLilac
import com.example.mytos.ui.theme.MytosLilacLight
import com.example.mytos.ui.theme.MytosPurple
import com.example.mytos.ui.theme.MytosPurpleDark
import com.example.mytos.ui.theme.MytosText
import com.example.mytos.ui.theme.MytosTextSecondary
import com.example.mytos.ui.theme.MytosYellow
import java.util.Locale

@Composable
fun CardapioScreen(
    navController: NavController
) {

    var mostrarDialogo by remember {
        mutableStateOf(false)
    }

    var busca by remember {
        mutableStateOf("")
    }

    var categoriaSelecionada by remember {
        mutableStateOf("Todos")
    }

    // ---------------------------------------------------------
    // PRODUTOS FILTRADOS
    // ---------------------------------------------------------

    val produtosFiltrados = produtos.filter { produto ->

        val correspondeBusca =
            produto.nome.contains(busca, ignoreCase = true) ||
                    produto.descricao.contains(busca, ignoreCase = true)

        val correspondeCategoria =
            when (categoriaSelecionada) {

                "Todos" -> true

                "Bebidas" -> produto.categoriaId == 1

                "Doces" -> produto.categoriaId == 2

                "Salgados" -> produto.categoriaId == 3

                else -> true
            }

        correspondeBusca && correspondeCategoria
    }

    // ---------------------------------------------------------
    // TELA
    // ---------------------------------------------------------

    androidx.compose.material3.Scaffold(
        containerColor = MytosCream,

        bottomBar = {
            MytosBottomBar(navController)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MytosCream),

            contentPadding = PaddingValues(
                bottom = 24.dp
            ),

            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // =================================================
            // CABEÇALHO
            // =================================================

            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 14.dp,
                            end = 14.dp,
                            top = 14.dp
                        )
                        .height(128.dp)
                        .clip(
                            RoundedCornerShape(26.dp)
                        )
                        .background(MytosPurpleDark)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = 20.dp,
                                end = 12.dp,
                                top = 16.dp,
                                bottom = 16.dp
                            )
                    ) {

                        // -----------------------------------------
                        // LINHA SUPERIOR
                        // -----------------------------------------

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = "MYTOS • CAFÉ E MITOLOGIA",
                                    color = MytosYellow,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.8.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(5.dp)
                                )

                                Text(
                                    text = "Nosso cardápio",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }

                            // -------------------------------------
                            // BOTÃO +
                            // -------------------------------------

                            IconButton(
                                onClick = {
                                    mostrarDialogo = true
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        color = MytosYellow,
                                        shape = CircleShape
                                    )
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Adicionar novo produto",
                                    tint = MytosPurpleDark,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(5.dp)
                        )

                        Text(
                            text = "Escolha sua próxima história para saborear.",
                            color = Color.White.copy(alpha = 0.78f),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // =================================================
            // BUSCA
            // =================================================

            item {

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                OutlinedTextField(
                    value = busca,
                    onValueChange = {
                        busca = it
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .height(54.dp),

                    singleLine = true,

                    placeholder = {
                        Text(
                            text = "O que você deseja saborear?",
                            color = MytosTextSecondary,
                            fontSize = 13.sp
                        )
                    },

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = MytosPurple
                        )
                    },

                    shape = RoundedCornerShape(17.dp),

                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MytosPurple,
                        unfocusedBorderColor = MytosLilac,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = MytosText,
                        unfocusedTextColor = MytosText
                    )
                )
            }

            // =================================================
            // CATEGORIAS
            // =================================================

            item {

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Text(
                    text = "Categorias",
                    color = MytosPurpleDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(
                            rememberScrollState()
                        )
                        .padding(horizontal = 14.dp),

                    horizontalArrangement =
                        Arrangement.spacedBy(8.dp)
                ) {

                    CategoriaFiltro(
                        nome = "Todos",
                        selecionada =
                            categoriaSelecionada == "Todos",
                        icon = Icons.Default.Category,
                        onClick = {
                            categoriaSelecionada = "Todos"
                        }
                    )

                    CategoriaFiltro(
                        nome = "Bebidas",
                        selecionada =
                            categoriaSelecionada == "Bebidas",
                        icon = Icons.Default.Coffee,
                        onClick = {
                            categoriaSelecionada = "Bebidas"
                        }
                    )

                    CategoriaFiltro(
                        nome = "Doces",
                        selecionada =
                            categoriaSelecionada == "Doces",
                        icon = Icons.Default.Cake,
                        onClick = {
                            categoriaSelecionada = "Doces"
                        }
                    )

                    CategoriaFiltro(
                        nome = "Salgados",
                        selecionada =
                            categoriaSelecionada == "Salgados",
                        icon = Icons.Default.Restaurant,
                        onClick = {
                            categoriaSelecionada = "Salgados"
                        }
                    )
                }
            }

            // =================================================
            // TÍTULO DOS PRODUTOS
            // =================================================

            item {

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Todos os sabores",
                        color = MytosPurpleDark,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "${produtosFiltrados.size} opções",
                        color = MytosTextSecondary,
                        fontSize = 11.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }

            // =================================================
            // LISTA DE PRODUTOS
            // =================================================

            items(
                items = produtosFiltrados,
                key = {
                    it.id
                }
            ) { produto ->

                ProdutoCardMytos(
                    produto = produto,

                    onClick = {
                        navController.navigate(
                            "produto/${produto.id}"
                        )
                    },

                    onDelete = {
                        produtos.remove(produto)
                    }
                )
            }

            // =================================================
            // NENHUM RESULTADO
            // =================================================

            if (produtosFiltrados.isEmpty()) {

                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 30.dp,
                                vertical = 50.dp
                            ),
                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Default.Coffee,
                            contentDescription = null,
                            tint = MytosPurple.copy(alpha = 0.4f),
                            modifier = Modifier.size(50.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Text(
                            text = "Nenhum sabor encontrado",
                            color = MytosPurpleDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(5.dp)
                        )

                        Text(
                            text = "Tente outra busca ou categoria.",
                            color = MytosTextSecondary,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }

    // =========================================================
    // DIALOGO DE NOVO PRODUTO
    // =========================================================

    if (mostrarDialogo) {

        NovoProdutoDialog(
            onDismiss = {
                mostrarDialogo = false
            },

            onAdicionar = { nome, descricao, preco, categoriaId ->

                val novoId =
                    (produtos.maxOfOrNull { it.id } ?: 0) + 1

                produtos.add(
                    Produto(
                        id = novoId,
                        nome = nome,
                        descricao = descricao,
                        preco = preco,
                        categoriaId = categoriaId
                    )
                )

                mostrarDialogo = false
            }
        )
    }
}


// =============================================================
// FILTRO DE CATEGORIA
// =============================================================

@Composable
private fun CategoriaFiltro(
    nome: String,
    selecionada: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (selecionada) {
                    MytosPurple
                } else {
                    Color.White
                }
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 14.dp,
                vertical = 9.dp
            ),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selecionada) {
                Color.White
            } else {
                MytosPurple
            },
            modifier = Modifier.size(15.dp)
        )

        Spacer(
            modifier = Modifier.width(5.dp)
        )

        Text(
            text = nome,
            color = if (selecionada) {
                Color.White
            } else {
                MytosText
            },
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


// =============================================================
// CARD DO PRODUTO
// =============================================================

@Composable
private fun ProdutoCardMytos(
    produto: Produto,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 14.dp,
                vertical = 5.dp
            )
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // -------------------------------------------------
            // IMAGEM / ÍCONE
            // -------------------------------------------------

            Box(
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(17.dp))
                    .background(MytosLilacLight),

                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(
                            Color.White,
                            CircleShape
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = if (
                            produto.categoriaId == 2
                        ) {
                            Icons.Default.Cake
                        } else {
                            Icons.Default.Coffee
                        },

                        contentDescription = null,

                        tint = MytosPurple,

                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            // -------------------------------------------------
            // INFORMAÇÕES
            // -------------------------------------------------

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = produto.nome,
                    color = MytosPurpleDark,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = produto.descricao,
                    color = MytosTextSecondary,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Text(
                    text = String.format(
                        Locale("pt", "BR"),
                        "R$ %.2f",
                        produto.preco
                    ),
                    color = MytosPurple,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            // -------------------------------------------------
            // LIXEIRA
            // -------------------------------------------------

            IconButton(
                onClick = {
                    onDelete()
                }
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remover ${produto.nome}",
                    tint = MytosTextSecondary.copy(alpha = 0.55f),
                    modifier = Modifier.size(21.dp)
                )
            }
        }
    }
}


// =============================================================
// DIALOGO — NOVO PRODUTO
// =============================================================

@Composable
private fun NovoProdutoDialog(
    onDismiss: () -> Unit,
    onAdicionar: (
        String,
        String,
        Double,
        Int
    ) -> Unit
) {

    var nome by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }

    var preco by remember {
        mutableStateOf("")
    }

    var categoriaSelecionada by remember {
        mutableStateOf(1)
    }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text(
                text = "Novo produto",
                color = MytosPurpleDark,
                fontWeight = FontWeight.ExtraBold
            )
        },

        text = {

            Column {

                OutlinedTextField(
                    value = nome,
                    onValueChange = {
                        nome = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text("Nome do produto")
                    }
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                OutlinedTextField(
                    value = descricao,
                    onValueChange = {
                        descricao = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 3,
                    label = {
                        Text("Descrição")
                    }
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                OutlinedTextField(
                    value = preco,
                    onValueChange = {
                        preco = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text("Preço")
                    }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Categoria",
                    color = MytosPurpleDark,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(
                            rememberScrollState()
                        ),
                    horizontalArrangement =
                        Arrangement.spacedBy(7.dp)
                ) {

                    CategoriaDialogButton(
                        nome = "Bebidas",
                        selecionada =
                            categoriaSelecionada == 1,
                        onClick = {
                            categoriaSelecionada = 1
                        }
                    )

                    CategoriaDialogButton(
                        nome = "Doces",
                        selecionada =
                            categoriaSelecionada == 2,
                        onClick = {
                            categoriaSelecionada = 2
                        }
                    )

                    CategoriaDialogButton(
                        nome = "Salgados",
                        selecionada =
                            categoriaSelecionada == 3,
                        onClick = {
                            categoriaSelecionada = 3
                        }
                    )
                }
            }
        },

        confirmButton = {

            Button(
                onClick = {

                    val precoNumerico =
                        preco
                            .replace(",", ".")
                            .toDoubleOrNull()

                    if (
                        nome.isNotBlank() &&
                        descricao.isNotBlank() &&
                        precoNumerico != null
                    ) {

                        onAdicionar(
                            nome.trim(),
                            descricao.trim(),
                            precoNumerico,
                            categoriaSelecionada
                        )
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = MytosPurple
                )
            ) {

                Text(
                    text = "Adicionar",
                    fontWeight = FontWeight.Bold
                )
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "Cancelar",
                    color = MytosPurple
                )
            }
        },

        containerColor = Color.White,
        shape = RoundedCornerShape(28.dp)
    )
}


// =============================================================
// CATEGORIA DO DIALOGO
// =============================================================

@Composable
private fun CategoriaDialogButton(
    nome: String,
    selecionada: Boolean,
    onClick: () -> Unit
) {

    Text(
        text = nome,

        color = if (selecionada) {
            Color.White
        } else {
            MytosPurple
        },

        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,

        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (selecionada) {
                    MytosPurple
                } else {
                    MytosLilac
                }
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 13.dp,
                vertical = 8.dp
            )
    )
}