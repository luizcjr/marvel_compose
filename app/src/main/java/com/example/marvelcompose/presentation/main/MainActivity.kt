package com.example.marvelcompose.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.marvelcompose.R
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.presentation.components.ErrorItem
import com.example.marvelcompose.presentation.components.LoadingItem
import com.example.marvelcompose.presentation.components.LoadingView
import com.example.marvelcompose.presentation.main.view.MainViewModel
import com.example.marvelcompose.presentation.theme.MarvelComposeTheme
import com.example.marvelcompose.presentation.theme.cardTitle
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
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
                        CharacterListLayout(viewModel.getAllCharacters(""), values)
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun CharacterListLayout(
    characterList: Flow<PagingData<Character>>?,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    characterList?.let { character ->
        val charactersLazyItems: LazyPagingItems<Character> = character.collectAsLazyPagingItems()
        LazyColumn(contentPadding = paddingValues) {
            items(charactersLazyItems) { item ->
                item?.let { character ->
                    CharacterItem(character = character, onClick = { })
                }
            }
            charactersLazyItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = charactersLazyItems.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = charactersLazyItems.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                onClickRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun CharacterItem(character: Character?, onClick: () -> Unit, modifier: Modifier = Modifier) {
    character?.let {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(8.dp)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (image, text) = createRefs()

                GlideImage(
                    imageModel = it.imageUrl,
                    modifier = modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                        .height(250.dp),
                    loading = {
                        LoadingItem()
                    },
                    failure = {
                        Text(text = "image request failed.")
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .alpha(.7f)
                        .constrainAs(text) {
                            bottom.linkTo(image.bottom)
                            end.linkTo(image.end)
                            start.linkTo(image.start)
                        },
                ) {
                    Text(
                        modifier = Modifier
                            .padding(all = 8.dp),
                        text = it.name,
                        color = Color.White,
                        style = cardTitle
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val characterReponseSpider = Character("Spider-Man", "spider-man.png")
    val characterReponseIronMan = Character("Iron-Man", "iron-man.png")
    val pagingDataCharacters = PagingData.from(
        listOf(
            characterReponseSpider,
            characterReponseIronMan
        )
    )

    MarvelComposeTheme {
        CharacterListLayout(flowOf(pagingDataCharacters))
    }
}