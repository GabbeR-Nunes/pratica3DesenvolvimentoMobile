package com.example.bibliotecapessoal.navigation

object Rotas {
    const val LISTA = "lista"
    const val CADASTRO = "cadastro"
    const val DETALHES = "detalhes/{itemId}"
    const val SOBRE = "sobre"
    fun detalhes(itemId: Long) = "detalhes/$itemId"
}
