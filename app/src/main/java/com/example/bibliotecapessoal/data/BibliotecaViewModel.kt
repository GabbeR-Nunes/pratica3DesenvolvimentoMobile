package com.example.bibliotecapessoal.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.bibliotecapessoal.model.ItemBiblioteca

class BibliotecaViewModel : ViewModel() {
    val itens = mutableStateListOf(
        ItemBiblioteca(1, "O Hobbit", "J. R. R. Tolkien", "Livro", 1937),
        ItemBiblioteca(2, "Harry Potter e a Pedra Filosofal", "J. K. Rowling", "Livro", 1997),
        ItemBiblioteca(3, "Interestelar", "Christopher Nolan", "DVD", 2014)
    )
    private var proximoId = 4L

    fun adicionar(titulo: String, autor: String, tipo: String, ano: Int) {
        itens.add(ItemBiblioteca(proximoId++, titulo, autor, tipo, ano))
    }
    fun buscarPorId(id: Long): ItemBiblioteca? = itens.find { it.id == id }
}
