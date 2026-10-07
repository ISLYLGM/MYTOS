package com.example.mytos.data

import androidx.compose.runtime.mutableStateListOf
import com.example.mytos.model.Produto

val produtos = mutableStateListOf(
    Produto(
        id = 1,
        nome = "Café de Apolo",
        descricao = "Café inspirado em Apolo, deus grego associado ao Sol.",
        preco = 14.90,
        categoriaId = 1
    ),
    Produto(
        id = 2,
        nome = "Néctar de Poseidon",
        descricao = "Bebida refrescante inspirada em Poseidon, deus dos mares.",
        preco = 16.90,
        categoriaId = 1
    ),
    Produto(
        id = 3,
        nome = "Torta de Hera",
        descricao = "Doce inspirado em Hera, rainha dos deuses gregos.",
        preco = 18.90,
        categoriaId = 2
    ),
    Produto(
        id = 4,
        nome = "Chá de Kitsune",
        descricao = "Chá inspirado nas lendárias kitsunes da mitologia japonesa.",
        preco = 13.90,
        categoriaId = 1
    )
)