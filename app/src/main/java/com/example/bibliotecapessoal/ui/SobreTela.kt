package com.example.bibliotecapessoal.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreTela(aoVoltar: () -> Unit) {
    val contexto = LocalContext.current
    Scaffold(topBar = { TopAppBar(title = { Text("Sobre / Ajuda") }) }) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Biblioteca Pessoal", style = MaterialTheme.typography.headlineSmall)
            Text("Finalidade: organizar livros, DVDs e outros itens da sua coleção pessoal.")
            Text("Como usar:")
            Text("1. Na tela inicial, veja os itens cadastrados.")
            Text("2. Toque em um item para consultar seus detalhes.")
            Text("3. Use “Cadastrar item” para adicionar um novo registro.")
            Text("4. Esta tela apresenta informações e permite compartilhar o projeto.")
            Text("Autores: [Digite aqui o nome dos integrantes]")
            Button(onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Biblioteca Pessoal: aplicativo Android feito em Kotlin e Jetpack Compose para cadastrar e consultar itens de uma biblioteca pessoal.")
                }
                try {
                    contexto.startActivity(Intent.createChooser(intent, "Compartilhar aplicativo"))
                } catch (_: ActivityNotFoundException) {
                    Toast.makeText(contexto, "Nenhum aplicativo disponível para compartilhar.", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Compartilhar aplicativo") }
            OutlinedButton(onClick = aoVoltar, modifier = Modifier.fillMaxWidth()) { Text("Voltar") }
        }
    }
}
