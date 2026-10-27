package com.example.mytos.data

import android.content.Context
import androidx.core.content.edit

class SessaoManager(context: Context) {

    private val preferencias = context.getSharedPreferences(
        "mytos_sessao",
        Context.MODE_PRIVATE
    )

    fun salvarUsuarioLogado(usuarioId: Long) {
        preferencias.edit {
            putLong(CHAVE_USUARIO_ID, usuarioId)
        }
    }

    fun obterUsuarioLogado(): Long? {
        val id = preferencias.getLong(CHAVE_USUARIO_ID, -1L)
        return id.takeIf { it > 0L }
    }

    fun encerrarSessao() {
        preferencias.edit {
            remove(CHAVE_USUARIO_ID)
        }
    }

    companion object {
        private const val CHAVE_USUARIO_ID = "usuario_id"
    }
}

