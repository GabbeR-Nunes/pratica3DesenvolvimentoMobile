package com.example.bibliotecapessoal.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroTela(aoSalvar: (String, String, String, Int) -> Unit, aoVoltar: () -> Unit) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var autor by rememberSaveable { mutableStateOf("") }
    var tipo by rememberSaveable { mutableStateOf("Livro") }
    var anoTexto by rememberSaveable { mutableStateOf("") }
    var erro by rememberSaveable { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("Cadastrar item") }) }) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(titulo, { titulo = it }, label = { Text("Título *") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(autor, { autor = it }, label = { Text("Autor") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(tipo, { tipo = it }, label = { Text("Tipo (Livro, DVD etc.)") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(anoTexto, { anoTexto = it.filter(Char::isDigit).take(4) }, label = { Text("Ano de publicação *") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            if (erro.isNotEmpty()) Text(erro, color = MaterialTheme.colorScheme.error)
            Button(onClick = {
                val ano = anoTexto.toIntOrNull()
                erro = when {
                    titulo.isBlank() -> "Informe o título do item."
                    ano == null || ano <= 0 || ano > 2100 -> "Informe um ano válido entre 1 e 2100."
                    else -> ""
                }
                if (erro.isEmpty()) aoSalvar(titulo.trim(), autor.trim().ifBlank { "Não informado" }, tipo.trim().ifBlank { "Outro" }, ano!!)
            }, modifier = Modifier.fillMaxWidth()) { Text("Salvar") }
            OutlinedButton(onClick = aoVoltar, modifier = Modifier.fillMaxWidth()) { Text("Cancelar") }
            Text("* Campo obrigatório")
        }
    }
}
