package com.robstore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.robstore.features.authentication.presentation.view.LoginScreen
import com.robstore.features.authentication.presentation.view.RecoveryPasswd
import com.robstore.features.authentication.presentation.view.RegisterScreen
import com.robstore.features.authentication.presentation.viewModel.LoginViewModel
import com.robstore.features.authentication.presentation.viewModel.LoginViewModelFactory
import com.robstore.features.core.SessionManager
import com.robstore.features.home.presentation.view.Home

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()



) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val factory = remember { LoginViewModelFactory(sessionManager) }
    val loginViewModel: LoginViewModel = viewModel(factory = factory)



    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {
        composable(NavigationRoutes.LOGIN) {
            LoginScreen(
                loginViewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate(NavigationRoutes.REGISTER) },
                onNavigateToRecoveryPasswd = { navController.navigate(NavigationRoutes.RecPasswd) },
                onNavigateToHome = {
                    navController.navigate(NavigationRoutes.HOME) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoutes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
        composable(NavigationRoutes.RecPasswd){
            RecoveryPasswd(
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
        composable(NavigationRoutes.HOME) {
            Home()
        }


    }
}
