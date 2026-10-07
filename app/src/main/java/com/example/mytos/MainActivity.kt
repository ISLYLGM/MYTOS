package com.example.mytos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mytos.navigation.Rotas
import com.example.mytos.screens.acessibilidade.AcessibilidadeScreen
import com.example.mytos.screens.cadastro.CadastroScreen
import com.example.mytos.screens.cardapio.CardapioScreen
import com.example.mytos.screens.home.HomeScreen
import com.example.mytos.screens.login.LoginScreen
import com.example.mytos.screens.perfil.PerfilScreen
import com.example.mytos.screens.produto.ProdutoDetalhesScreen
import com.example.mytos.screens.splash.SplashScreen
import com.example.mytos.ui.theme.MytosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MytosTheme {
                MytosApp()
            }
        }
    }
}

@Composable
fun MytosApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rotas.Splash
    ) {

        // =========================
        // SPLASH
        // =========================

        composable(Rotas.Splash) {

            SplashScreen(
                onFinished = {
                    navController.navigate(Rotas.Login) {
                        popUpTo(Rotas.Splash) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // =========================
        // LOGIN
        // =========================

        composable(Rotas.Login) {

            LoginScreen(
                onLogin = {
                    navController.navigate(Rotas.Home) {
                        popUpTo(Rotas.Login) {
                            inclusive = true
                        }
                    }
                },
                onCadastro = {
                    navController.navigate(Rotas.Cadastro)
                }
            )
        }

        // =========================
        // CADASTRO
        // =========================

        composable(Rotas.Cadastro) {

            CadastroScreen(
                onCadastroConcluido = {
                    navController.navigate(Rotas.Acessibilidade) {
                        popUpTo(Rotas.Cadastro) {
                            inclusive = true
                        }
                    }
                },
                onVoltarLogin = {
                    navController.popBackStack()
                }
            )
        }

        // =========================
        // ACESSIBILIDADE
        // =========================

        composable(Rotas.Acessibilidade) {

            AcessibilidadeScreen(
                onContinuar = {
                    navController.navigate(Rotas.Home) {
                        popUpTo(Rotas.Acessibilidade) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // =========================
        // HOME
        // =========================

        composable(Rotas.Home) {

            HomeScreen(
                navController = navController
            )
        }

        // =========================
        // CARDÁPIO
        // =========================

        composable(Rotas.Cardapio) {

            CardapioScreen(
                navController = navController
            )
        }

        // =========================
        // DETALHES DO PRODUTO
        // =========================

        composable(
            route = Rotas.ProdutoDetalhes,
            arguments = listOf(
                navArgument("produtoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val produtoId =
                backStackEntry.arguments?.getInt("produtoId")

            if (produtoId != null) {

                ProdutoDetalhesScreen(
                    navController = navController,
                    produtoId = produtoId
                )
            }
        }

        // =========================
        // PERFIL
        // =========================

        composable(Rotas.Perfil) {

            PerfilScreen(
                navController = navController
            )
        }
    }
}