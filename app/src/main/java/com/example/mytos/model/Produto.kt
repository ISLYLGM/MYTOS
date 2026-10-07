package com.example.mytos.model
import java.util.Date
data class Produto(
    val id: Int,
    val nome: String,
    val descricao: String,
    val preco: Double,
    val categoriaId: Int
)

data class ItemSacola(
    val produto: Produto,
    val quantidade: Int
) {
    val subtotal: Double
        get() = produto.preco * quantidade
}

data class Pedido(
    val id: String,
    val itens: List<ItemSacola>,
    val valorTotal: Double,
    val data: Date = Date()
)
