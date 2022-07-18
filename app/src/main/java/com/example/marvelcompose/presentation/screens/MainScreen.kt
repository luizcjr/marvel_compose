package com.example.marvelcompose.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.presentation.navigation.NavigationRoutes
import kotlinx.coroutines.flow.Flow

@ExperimentalMaterial3Api
@Composable
fun ScreenMain(
    characterList: Flow<PagingData<Character>>?,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationRoutes.Characters.route) {
        composable(NavigationRoutes.Characters.route) {
            CharacterListLayout(characterList, paddingValues, navController)
        }

        composable(NavigationRoutes.CharacterDetails.route + "/{id}") { navBackStack ->
            val id = navBackStack.arguments?.getString("id")
            CharacterDetailsLayout()
        }
    }
}