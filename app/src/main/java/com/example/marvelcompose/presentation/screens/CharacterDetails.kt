package com.example.marvelcompose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.marvelcompose.domain.model.Details
import com.example.marvelcompose.presentation.activity.view.model.CharacterDetails
import com.example.marvelcompose.presentation.components.LoadingItem
import com.example.marvelcompose.presentation.components.LoadingView
import com.example.marvelcompose.presentation.theme.cardTitle
import com.example.marvelcompose.presentation.theme.descriptionStyle
import com.example.marvelcompose.presentation.theme.homeSubtitle
import com.example.marvelcompose.presentation.theme.nameTitle
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterial3Api
@Composable
fun CharacterDetailsLayout(
    loadingState: State<Boolean?>,
    comicsState: State<CharacterDetails?>,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    if (loadingState.value == true) {
        LoadingView(modifier = Modifier.fillMaxSize())
    }

    comicsState.value?.let { comic ->
        val comicsLazyItems: LazyPagingItems<Details> = comic.comics.collectAsLazyPagingItems()
        val seriesLazyItems: LazyPagingItems<Details> = comic.series.collectAsLazyPagingItems()
        val eventsLazyItems: LazyPagingItems<Details> = comic.events.collectAsLazyPagingItems()
        comic.character.firstNotNullOf {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
            ) {
                item {
                    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                        val (
                            image,
                            name,
                            descriptionLabel,
                            description,
                            comicsLabel,
                            comicsList,
                            seriesLabel,
                            seriesList,
                            eventsLabel,
                            eventsList
                        ) = createRefs()

                        GlideImage(
                            imageModel = it.imageUrl,
                            modifier = modifier
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                }
                                .height(400.dp),
                            loading = {
                                LoadingItem()
                            },
                            failure = {
                                Text(text = "image request failed.")
                            }
                        )

                        Text(
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .fillMaxWidth()
                                .constrainAs(name) {
                                    top.linkTo(image.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                },
                            text = it.name,
                            color = Color.White,
                            style = nameTitle,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .fillMaxWidth()
                                .constrainAs(descriptionLabel) {
                                    top.linkTo(name.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                },
                            text = "Descrição",
                            color = Color.White,
                            style = cardTitle,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            modifier = Modifier
                                .padding(top = 4.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .constrainAs(description) {
                                    top.linkTo(descriptionLabel.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                },
                            text = it.description,
                            color = Color.White,
                            style = descriptionStyle,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            modifier = Modifier
                                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .constrainAs(comicsLabel) {
                                    top.linkTo(description.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                },
                            text = "Quadrinho${if (comicsLazyItems.itemCount > 1) "s" else ""}",
                            color = Color.White,
                            style = cardTitle
                        )

                        LazyRow(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .constrainAs(comicsList) {
                                    top.linkTo(comicsLabel.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                }) {
                            items(comicsLazyItems.itemCount) { comic ->
                                DetailsLayout(details = comicsLazyItems[comic])
                            }
                        }

                        Text(
                            modifier = Modifier
                                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .constrainAs(seriesLabel) {
                                    top.linkTo(comicsList.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                },
                            text = "Série${if (seriesLazyItems.itemCount > 1) "s" else ""}",
                            color = Color.White,
                            style = cardTitle
                        )

                        LazyRow(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .constrainAs(seriesList) {
                                    top.linkTo(seriesLabel.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                }) {
                            items(seriesLazyItems.itemCount) { series ->
                                DetailsLayout(details = seriesLazyItems[series])
                            }
                        }

                        Text(
                            modifier = Modifier
                                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .constrainAs(eventsLabel) {
                                    top.linkTo(seriesList.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                },
                            text = "Evento${if (eventsLazyItems.itemCount > 1) "s" else ""}",
                            color = Color.White,
                            style = cardTitle
                        )

                        LazyRow(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .constrainAs(eventsList) {
                                    top.linkTo(eventsLabel.bottom)
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                }) {
                            items(eventsLazyItems.itemCount) { stories ->
                                DetailsLayout(details = eventsLazyItems[stories])
                            }
                        }
                    }
                }
            }
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun DetailsLayout(details: Details?, modifier: Modifier = Modifier) {
    details?.let {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(Color.Black)
                .wrapContentHeight()
                .width(200.dp),
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
                        .background(Color.White)
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
                        text = it.title,
                        color = Color.Black,
                        style = homeSubtitle
                    )
                }
            }
        }
    }
}
