package com.example.marvelcompose.presentation.navigation

sealed class NavigationRoutes(val route: String) {
        object Characters : NavigationRoutes("characters")
        object CharacterDetails : NavigationRoutes("character-details")
}