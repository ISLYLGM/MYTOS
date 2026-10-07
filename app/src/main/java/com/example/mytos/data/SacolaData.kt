package com.example.mytos.data

import androidx.compose.runtime.mutableStateListOf
import com.example.mytos.model.ItemSacola
import com.example.mytos.model.Pedido
import com.example.mytos.model.Produto
import java.util.UUID

object SacolaData {
    // Listas observáveis pelo Jetpack Compose
    val itensSacola = mutableStateListOf<ItemSacola>()
    val pedidos = mutableStateListOf<Pedido>()

    fun adicionarProduto(produto: Produto) {
        val index = itensSacola.indexOfFirst { it.produto.id == produto.id }
        if (index != -1) {
            val itemAtual = itensSacola[index]
            itensSacola[index] = itemAtual.copy(quantidade = itemAtual.quantidade + 1)
        } else {
            itensSacola.add(ItemSacola(produto = produto, quantidade = 1))
        }
    }

    fun aumentarQuantidade(produtoId: Int) {
        val index = itensSacola.indexOfFirst { it.produto.id == produtoId }
        if (index != -1) {
            val itemAtual = itensSacola[index]
            itensSacola[index] = itemAtual.copy(quantidade = itemAtual.quantidade + 1)
        }
    }

    fun diminuirQuantidade(produtoId: Int) {
        val index = itensSacola.indexOfFirst { it.produto.id == produtoId }
        if (index != -1) {
            val itemAtual = itensSacola[index]
            val novaQtd = itemAtual.quantidade - 1
            if (novaQtd > 0) {
                itensSacola[index] = itemAtual.copy(quantidade = novaQtd)
            } else {
                itensSacola.removeAt(index) // Remove quando chega a zero
            }
        }
    }

    fun calcularTotal(): Double {
        return itensSacola.sumOf { it.subtotal }
    }

    fun finalizarPedido(): Pedido? {
        if (itensSacola.isEmpty()) return null

        val novoPedido = Pedido(
            id = UUID.randomUUID().toString().take(8),
            itens = itensSacola.toList(),
            valorTotal = calcularTotal()
        )

        pedidos.add(novoPedido)
        itensSacola.clear() // Limpa a sacola após finalizar
        return novoPedido
    }
}