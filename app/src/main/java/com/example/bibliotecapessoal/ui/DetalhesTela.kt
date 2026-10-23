package com.example.bibliotecapessoal.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bibliotecapessoal.model.ItemBiblioteca

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesTela(item: ItemBiblioteca?, aoVoltar: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Detalhes do item") }) }) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (item == null) Text("Não foi encontrado um item com esse identificador.")
            else {
                Text("ID: ${item.id}", style = MaterialTheme.typography.bodyMedium)
                Text("Título: ${item.titulo}", style = MaterialTheme.typography.titleLarge)
                Text("Autor: ${item.autor}")
                Text("Tipo: ${item.tipo}")
                Text("Ano de publicação: ${item.ano}")
            }
            Button(onClick = aoVoltar) { Text("Voltar") }
        }
    }
}
