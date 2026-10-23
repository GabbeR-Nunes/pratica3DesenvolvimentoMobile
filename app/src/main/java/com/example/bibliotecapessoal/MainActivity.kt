package com.example.bibliotecapessoal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bibliotecapessoal.data.BibliotecaViewModel
import com.example.bibliotecapessoal.navigation.Rotas
import com.example.bibliotecapessoal.ui.CadastroTela
import com.example.bibliotecapessoal.ui.DetalhesTela
import com.example.bibliotecapessoal.ui.ListaTela
import com.example.bibliotecapessoal.ui.SobreTela

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val bibliotecaViewModel: BibliotecaViewModel = viewModel()
            MaterialTheme {
                Surface {
                    NavHost(navController = navController, startDestination = Rotas.LISTA) {
                        composable(Rotas.LISTA) {
                            ListaTela(
                                itens = bibliotecaViewModel.itens,
                                aoAbrirCadastro = { navController.navigate(Rotas.CADASTRO) },
                                aoAbrirSobre = { navController.navigate(Rotas.SOBRE) },
                                aoAbrirDetalhes = { id -> navController.navigate(Rotas.detalhes(id)) }
                            )
                        }
                        composable(Rotas.CADASTRO) {
                            CadastroTela(
                                aoSalvar = { titulo, autor, tipo, ano ->
                                    bibliotecaViewModel.adicionar(titulo, autor, tipo, ano)
                                    navController.popBackStack()
                                },
                                aoVoltar = { navController.popBackStack() }
                            )
                        }
                        composable(Rotas.DETALHES, arguments = listOf(navArgument("itemId") { type = NavType.LongType })) { entrada ->
                            val id = entrada.arguments?.getLong("itemId") ?: -1L
                            DetalhesTela(bibliotecaViewModel.buscarPorId(id)) { navController.popBackStack() }
                        }
                        composable(Rotas.SOBRE) {
                            SobreTela { navController.popBackStack() }
                        }
                    }
                }
            }
        }
    }
}
