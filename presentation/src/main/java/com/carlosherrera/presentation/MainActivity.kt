package com.carlosherrera.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlosherrera.presentation.ui.screens.detail.DetailScreen
import com.carlosherrera.presentation.ui.screens.main.ProductsScreen
import com.carlosherrera.presentation.ui.theme.GoliathNationalBankTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoliathNationalBankTheme {
                Scaffold(topBar = {
                    TopAppBar(title = { Text(stringResource(id = R.string.activity_name)) })
                }) {
                    NavSystem()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NavSystem() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            ProductsScreen(onClick = {
                navController.navigate("detail/" + it.sku)
            })
        }
        composable("detail/{sku}", arguments = listOf(navArgument("sku") { type = NavType.StringType })) {
            val sku = it.arguments?.getString("sku") ?: ""
            DetailScreen(sku)
        }
    }
}