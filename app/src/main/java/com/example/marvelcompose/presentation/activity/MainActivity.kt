package com.example.marvelcompose.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import com.example.marvelcompose.R
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.presentation.activity.view.MainViewModel
import com.example.marvelcompose.presentation.screens.CharacterListLayout
import com.example.marvelcompose.presentation.screens.ScreenMain
import com.example.marvelcompose.presentation.theme.MarvelComposeTheme
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            SmallTopAppBar(
                                title = {
                                    Row {
                                        Image(
                                            modifier = Modifier
                                                .align(CenterVertically)
                                                .fillMaxWidth(),
                                            painter =
                                            painterResource(id = R.drawable.ic_app_bar_logo),
                                            contentDescription = "Logo da Marvel"
                                        )
                                    }
                                },
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                )
                            )
                        }
                    ) { values ->
                        ScreenMain(
                            viewModel = viewModel,
                            paddingValues = values
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val characterReponseSpider = Character(1, "Spider-Man", "description", "spider-man.png")
    val characterReponseIronMan = Character(2, "Iron-Man", "description", "iron-man.png")
    val pagingDataCharacters = PagingData.from(
        listOf(
            characterReponseSpider,
            characterReponseIronMan
        )
    )

    MarvelComposeTheme {
        CharacterListLayout(mutableStateOf(flowOf(pagingDataCharacters)), navController = null)
    }
}