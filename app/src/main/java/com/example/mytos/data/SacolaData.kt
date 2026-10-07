package com.example.mytos.data

import androidx.compose.runtime.mutableStateMapOf

// Guarda a quantidade de cada produto na sacola.
// Exemplo:
// produto 1 → 2 unidades
// produto 3 → 1 unidade
val sacola = mutableStateMapOf<Int, Int>()


// =============================================================
// ADICIONAR PRODUTO
// =============================================================

fun adicionarNaSacola(produtoId: Int) {
    sacola[produtoId] = (sacola[produtoId] ?: 0) + 1
}


// =============================================================
// DIMINUIR PRODUTO
// =============================================================

fun diminuirDaSacola(produtoId: Int) {

    val quantidadeAtual = sacola[produtoId] ?: return

    if (quantidadeAtual <= 1) {

        // Se chegou a zero, remove da sacola
        sacola.remove(produtoId)

    } else {

        sacola[produtoId] = quantidadeAtual - 1
    }
}


// =============================================================
// QUANTIDADE DE UM PRODUTO
// =============================================================

fun quantidadeNaSacola(produtoId: Int): Int {
    return sacola[produtoId] ?: 0
}