package com.example.marvelcompose.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.presentation.activity.view.MainViewModel
import com.example.marvelcompose.presentation.navigation.NavigationRoutes

@ExperimentalMaterial3Api
@Composable
fun ScreenMain(
    viewModel: MainViewModel,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationRoutes.Characters.route) {
        composable(NavigationRoutes.Characters.route) {
            CharacterListLayout(
                mutableStateOf(viewModel.getAllCharacters("")),
                paddingValues,
                navController
            )
        }

        composable(NavigationRoutes.CharacterDetails.route + "/{id}") { navBackStack ->
            val id = navBackStack.arguments?.getString("id")
            id?.let {
                viewModel.getCharacterById(it.toInt())
                CharacterDetailsLayout(
                    viewModel.character.observeAsState(),
                    viewModel.loading.observeAsState(),
                    paddingValues
                )
            }
        }
    }
}