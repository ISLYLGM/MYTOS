package com.example.mytos.model

data class Produto(
    val id: Int,
    val nome: String,
    val descricao: String,
    val preco: Double,
    val categoriaId: Int
)