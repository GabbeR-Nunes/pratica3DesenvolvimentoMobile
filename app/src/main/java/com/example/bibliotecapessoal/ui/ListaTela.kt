package com.example.bibliotecapessoal.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bibliotecapessoal.model.ItemBiblioteca

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTela(itens: List<ItemBiblioteca>, aoAbrirCadastro: () -> Unit, aoAbrirSobre: () -> Unit, aoAbrirDetalhes: (Long) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Biblioteca Pessoal") }) }) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = aoAbrirCadastro, modifier = Modifier.weight(1f)) { Text("Cadastrar item") }
                OutlinedButton(onClick = aoAbrirSobre, modifier = Modifier.weight(1f)) { Text("Sobre / Ajuda") }
            }
            Spacer(Modifier.height(12.dp))
            if (itens.isEmpty()) Text("Nenhum item cadastrado.")
            else LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(itens, key = { it.id }) { item ->
                    Card(Modifier.fillMaxWidth().clickable { aoAbrirDetalhes(item.id) }) {
                        Column(Modifier.padding(16.dp)) {
                            Text(item.titulo, style = MaterialTheme.typography.titleMedium)
                            Text("Autor: ${item.autor}")
                            Text("Tipo: ${item.tipo} • Ano: ${item.ano}")
                            Spacer(Modifier.height(4.dp))
                            TextButton(onClick = { aoAbrirDetalhes(item.id) }) { Text("Ver detalhes") }
                        }
                    }
                }
            }
        }
    }
}
